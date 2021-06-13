package com.example.management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private ArrayList<FoodItem> data = null; //Item 목록을 담을 배열
    private int layout;
    private int nListcnt = 0;


    public FoodAdapter(ArrayList<FoodItem> aaaa) {
        data = aaaa;
        nListcnt = data.size();

    }

    public FoodAdapter(Context context, int layout, ArrayList<FoodItem> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() { //리스트 안 Item의 개수를 센다.
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        FoodItem foodItem = data.get(position);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(foodItem.getName());

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(foodItem.getDate());


        return convertView;
    }
}