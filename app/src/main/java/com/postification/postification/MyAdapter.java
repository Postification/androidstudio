package com.postification.postification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater ;
    ArrayList<Baggage> baggagesList=null;

    public MyAdapter(Context context,ArrayList<Baggage> baggageList) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.baggagesList=baggageList;
    }

    @Override
    public int getCount() {
        return baggagesList.size();
    }

    @Override
    public Object getItem(int position) {
        return baggagesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return baggagesList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.baggage_list,parent,false);

        ((TextView)convertView.findViewById(R.id.name)).setText(baggagesList.get(position).getName());
        ((TextView)convertView.findViewById(R.id.weight)).setText(String.valueOf(baggagesList.get(position).getWeight()));
        ((TextView)convertView.findViewById(R.id.time)).setText((baggagesList.get(position).getTime()));

        return convertView;
    }
}
