package com.postification.postification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ChangeLayoutActivity changeLayoutActivity=new ChangeLayoutActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<Baggage> list =new ArrayList<>();
        Baggage baggage=new Baggage();

        listView=findViewById(R.id.listView);

        //初期値代入
        baggage.setName("荷物");
        baggage.setTime("0");
        baggage.setWeight(0);
        for(int i=0;i<5;i++){
            list.add(baggage);
        }

        MyAdapter myAdapter=new MyAdapter(ListActivity.this,list);
        listView.setAdapter(myAdapter);

        changeLayoutActivity.listLayout(list,myAdapter);
    }
}
