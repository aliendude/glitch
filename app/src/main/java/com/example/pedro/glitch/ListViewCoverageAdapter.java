package com.example.pedro.glitch;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pedro.myapplication.backend1.coverages.model.Coverage;


import java.util.ArrayList;

public class ListViewCoverageAdapter extends BaseAdapter {
    private static ArrayList<Coverage> listCoverages;

    private LayoutInflater mInflater;

    public ListViewCoverageAdapter(Context photosFragment, ArrayList<Coverage> results) {
        listCoverages = results;
        mInflater = LayoutInflater.from(photosFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listCoverages.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listCoverages.get(arg0);
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
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.subscription_list_element, null);
            holder = new ViewHolder();


            holder.txtHashTag = (TextView) convertView.findViewById(R.id.coverage_hashtag_text);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.coverage_description_text);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtHashTag.setText(listCoverages.get(position).getHashtag());
        holder.txtDescription.setText(listCoverages.get(position).getDescription());

        return convertView;
    }

    static class ViewHolder {
        TextView txtHashTag, txtDescription;
    }
}