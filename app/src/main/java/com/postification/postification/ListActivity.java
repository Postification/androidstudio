package com.postification.postification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class ListActivity extends AppCompatActivity {

    private static final String[] texts = {
            "abc ", "bcd", "cde", "def", "efg",
            "fgh", 	"ghi", "hij", "ijk", "jkl",
            "klm"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

         ListView listView = new ListView(this);
        setContentView(listView);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, texts);

        listView.setAdapter(arrayAdapter);
    }
}
