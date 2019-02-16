package com.postification.postification;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity activity=getActivity();
        ArrayList<Baggage> list =new ArrayList<>();
        Baggage baggage=new Baggage();
        ListView listView=activity.findViewById(R.id.listView);

        //初期値代入
        baggage.setName("");
        baggage.setTime("");
        baggage.setWeight("");
        for(int i=0;i<10;i++){
            list.add(baggage);
        }

        Context context=this.getActivity().getBaseContext();
        MyAdapter myAdapter=new MyAdapter(context,list);
        listView.setAdapter(myAdapter);

        changeList(list,myAdapter);
    }

    public void changeList(final ArrayList<Baggage> list, final MyAdapter myAdapter){

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/Post/list/");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i;
                for(i=1;i<=10;i++){
                    String childAdrr="baggage"+String.valueOf(i);
                    Baggage baggage=new Baggage();
                    String time = dataSnapshot.child(childAdrr).child("time").getValue(String.class);
                    int intWeight = dataSnapshot.child(childAdrr).child("weight").getValue(int.class);
                    String weight=String.valueOf(intWeight);

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
