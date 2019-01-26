package com.postification.postification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private TextView textView;
    private Button button;
    private String id="id_postification_test";
    private String name="name_postification_test";
    private ChangeLayoutActivity changeLayoutActivity=new ChangeLayoutActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Firebase Analytics
        //ユーザー情報の送信
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Firebase Database
        //DatabaseReference myRef = database.getReference("/Post/weight/");
        //myRef.setValue(4649);

        changeLayoutActivity.mainLayout(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
