package com.postification.postification;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        ChangeLayoutActivity changeLayoutActivity=new ChangeLayoutActivity();
        ArrayList<Baggage> list =new ArrayList<>();
        Baggage baggage=new Baggage();
        ListView listView=activity.findViewById(R.id.listView);

        //初期値代入
        baggage.setName("荷物");
        baggage.setTime("0");
        baggage.setWeight(0);
        for(int i=0;i<5;i++){
            list.add(baggage);
        }

        Context context=this.getActivity().getBaseContext();
        MyAdapter myAdapter=new MyAdapter(context,list);
        listView.setAdapter(myAdapter);

        changeLayoutActivity.listLayout(list,myAdapter);
    }
}
