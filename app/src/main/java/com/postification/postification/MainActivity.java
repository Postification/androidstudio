package com.postification.postification;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    NavigationView navigationView;
    String fragmentName="Postification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout, mainFragment,fragmentName);
        fragmentTransaction.commit();
        navigationView.getMenu().getItem(0).setChecked(true);

        //Firebase Analytics
        FirebaseAnalytics mFirebaseAnalytics=FirebaseAnalytics.getInstance(this);
        String id="TestDevice";
        String name="android_application_Postification";

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    public void onStart(){
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment fragment=null;
        FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();

        if (id == R.id.nav_home) {
            fragmentName = "Postification";
            fragment = getFragmentManager().findFragmentByTag(fragmentName);
            if (fragment == null) {
                fragment =new MainFragment();
            }
            navigationView.getMenu().getItem(0).setChecked(true);

        }else if (id == R.id.nav_baggage) {
            fragmentName = "宅配物";
            fragment = getFragmentManager().findFragmentByTag(fragmentName);
            if (fragment == null) {
                fragment =new ListFragment();
            }
            navigationView.getMenu().getItem(1).setChecked(true);

        } else if (id == R.id.nav_setting) {
            fragmentName = "設定";
            fragment = getFragmentManager().findFragmentByTag(fragmentName);
            if (fragment == null) {
                fragment =new SettingFragment();
            }
            navigationView.getMenu().getItem(2).setChecked(true);
        }

        fragmentTransaction.replace(R.id.fragmentLayout, fragment,fragmentName);
        fragmentTransaction.commit();
        toolbar.setTitle(fragmentName);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
