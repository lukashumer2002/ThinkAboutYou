package com.example.thinkaboutyou;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends ArrayAdapter {

    private ArrayList<Meal> topicList;
    private Context mContext;
    private int layoutid;
    public MealAdapter(@NonNull Context context, ArrayList<Meal> list) {
        super(context, 0 , list);
        mContext = context;
        topicList= list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.mealview,parent,false);

        Meal currentNote = topicList.get(position);

        TextView topic = (TextView) listItem.findViewById(R.id.addnewmealName);
        topic.setText(currentNote.getMeal());


        TextView checknumber = (TextView) listItem.findViewById(R.id.addnewmealKcal);
        checknumber.setText(currentNote.getKcal());

        return listItem;
    }
}
