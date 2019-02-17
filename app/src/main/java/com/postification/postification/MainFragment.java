package com.postification.postification;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainFragment extends Fragment {

    ImageView imageView;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Activity activity = getActivity();
        imageView= activity.findViewById(R.id.imageView);
        textView=activity.findViewById(R.id.textView_Nitem);

        changeButtonText();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
                toolbar.setTitle("宅配物");
                navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
                navigationView.getMenu().getItem(1).setChecked(true);

                Fragment listFragment = new ListFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentLayout, listFragment, "宅配物");
                fragmentTransaction.commit();
            }
        });
    }

    public void changeButtonText() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/Post/quantity/");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int quantity = dataSnapshot.getValue(int.class);
                Log.d("GetData Succesful", "quantity=" + quantity);

                textView.setText(String.valueOf(quantity));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("GetData ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        ref.addListenerForSingleValueEvent(postListener);
    }
}
