package com.postification.postification;

import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChangeLayoutActivity {

    public void mainLayout(final Button button){

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/Post/quantity/");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int quantity = dataSnapshot.getValue(int.class);
                Log.d("GetData Succesful", "quantity="+quantity);

                button.setText(String.valueOf(quantity));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("GetData ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        ref.addListenerForSingleValueEvent(postListener);
    }

    public void listLayout(final ArrayList<Baggage> list, final MyAdapter myAdapter){

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/Post/list/");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i;
                for(i=1;i<6;i++){
                    String childAdrr="baggage"+String.valueOf(i);
                    Baggage baggage=new Baggage();
                    String time = dataSnapshot.child(childAdrr).child("time").getValue(String.class);
                    int weight = dataSnapshot.child(childAdrr).child("weight").getValue(int.class);

                    baggage.setName("荷物"+String.valueOf(i));
                    baggage.setTime(time);
                    baggage.setWeight(weight);

                    list.set(i-1,baggage);
                }
                myAdapter.notifyDataSetChanged();

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