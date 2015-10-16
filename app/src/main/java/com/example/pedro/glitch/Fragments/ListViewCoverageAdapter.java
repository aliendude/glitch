package com.example.pedro.glitch.Fragments;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pedro.glitch.R;
import com.example.pedro.myapplication.backend1.coverages.model.Coverage;


import java.util.ArrayList;

public class ListViewCoverageAdapter extends BaseAdapter {
private static ArrayList<Coverage> listMarkers;

private LayoutInflater mInflater;

public ListViewCoverageAdapter(Context photosFragment, ArrayList<Coverage> results){
    listMarkers = results;
    mInflater = LayoutInflater.from(photosFragment);
}

@Override
public int getCount() {
    // TODO Auto-generated method stub
    return listMarkers.size();
}

@Override
public Object getItem(int arg0) {
    // TODO Auto-generated method stub
    return listMarkers.get(arg0);
}

@Override
public long getItemId(int arg0) {
    // TODO Auto-generated method stub
    return arg0;
}

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }
    public View getView(int position, View convertView, ViewGroup parent) {

    ViewHolder holder;
    if(convertView == null){
        convertView = mInflater.inflate(R.layout.subscription_list_element, null);
        holder = new ViewHolder();


        holder.txtname = (TextView) convertView.findViewById(R.id.map_marker_username_text);
        holder.txtphone = (TextView) convertView.findViewById(R.id.map_marker_description_text);

        convertView.setTag(holder);
    } else {
        holder = (ViewHolder) convertView.getTag();
    }

    holder.txtname.setText(listMarkers.get(position).getName());
    holder.txtphone.setText(listMarkers.get(position).getDescription());

    return convertView;
}

static class ViewHolder{
    TextView txtname, txtphone;
}
}