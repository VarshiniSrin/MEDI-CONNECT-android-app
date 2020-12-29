package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Items_DisplayReports> feedList;

    public CustomAdapter(Context context, ArrayList<Items_DisplayReports> data){
        this.context=context;
        this.feedList = data;
    }

    @Override
    public int getCount() {
        return feedList.size();
    }

    @Override
    public Object getItem(int position) {
        return feedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final Holder holder;
        Holder h1;
        Items_DisplayReports data = DisplayReports.items.get(position);

        if(view == null){

            holder = new Holder();
            LayoutInflater inflater =
                    (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.listview_displayreports,null);

            view.setTag(holder);
            holder.time = view.findViewById(R.id.time);
            holder.disease = view.findViewById(R.id.disease);
            holder.symptoms = view.findViewById(R.id.symptoms);

        }
        else{
            holder = (Holder)view.getTag();
        }

        holder.time.setText(data.getTime());
        holder.disease.setText(data.getDisease());
        holder.symptoms.setText(data.getSymptoms());
//
        return  view;
    }

    private class Holder {
        TextView time;
        TextView disease;
        TextView symptoms;
    }
}