package com.postification.postification;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private int quantity=0;
    private int receive=-1;
    Context context;
    private String weight;
    private LayoutInflater layoutInflater ;
    private ArrayList<Baggage> baggageList=null;
    private int baggageSize=0;
    private Activity activity;
    private int bigSize,smallSize;
    private static final String  databaseName = "setting";

    MyAdapter(Context context, ArrayList<Baggage> baggageList, Activity activity) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.baggageList=baggageList;
        this.activity=activity;

        SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
        bigSize=data.getInt("list_size_big",300);
        smallSize=data.getInt("list_size_small",50);
    }

    @Override
    public int getCount() {
        return baggageList.size();
    }

    @Override
    public Object getItem(int position) {
        return baggageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return baggageList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        baggageSize=baggageList.get(position).getIntWeight();
        weight=baggageList.get(position).getWeight()+" [g]";

        if(quantity!=0&&position==0){
            convertView = layoutInflater.inflate(R.layout.receive_baggage_list,parent,false);
            ((TextView)convertView.findViewById(R.id.receive)).setText("未受取");
            ((TextView)convertView.findViewById(R.id.name)).setText(baggageList.get(position).getName());
            ((TextView)convertView.findViewById(R.id.weight)).setText(weight);
            ((TextView)convertView.findViewById(R.id.time)).setText((baggageList.get(position).getTime()));

        }else if(quantity==0||receive==position){
            convertView = layoutInflater.inflate(R.layout.receive_baggage_list,parent,false);
            ((TextView)convertView.findViewById(R.id.receive)).setText("受取済");
            ((TextView)convertView.findViewById(R.id.name)).setText(baggageList.get(position).getName());
            ((TextView)convertView.findViewById(R.id.weight)).setText(weight);
            ((TextView)convertView.findViewById(R.id.time)).setText((baggageList.get(position).getTime()));
            receive=position;
        }else{
            convertView = layoutInflater.inflate(R.layout.baggage_list,parent,false);
            ((TextView)convertView.findViewById(R.id.name)).setText(baggageList.get(position).getName());
            ((TextView)convertView.findViewById(R.id.weight)).setText(weight);
            ((TextView)convertView.findViewById(R.id.time)).setText((baggageList.get(position).getTime()));
        }

        setImageView(baggageSize,convertView);
        quantity--;

        return convertView;
    }

    private void setImageView(int size,View view){
        if(baggageSize<=smallSize){
            ((ImageView)view.findViewById(R.id.imageView)).setImageResource(R.drawable.circle_small);
        }else if(baggageSize<bigSize){
            ((ImageView)view.findViewById(R.id.imageView)).setImageResource(R.drawable.circle_medium);
        }else{
            ((ImageView)view.findViewById(R.id.imageView)).setImageResource(R.drawable.circle_big);
        }
    }

    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
}
