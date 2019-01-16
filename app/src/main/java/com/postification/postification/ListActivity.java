package com.postification.postification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


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

        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListView listView = new ListView(this);
        setContentView(listView);

        // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, texts);

        listView.setAdapter(arrayAdapter);
    }
}
