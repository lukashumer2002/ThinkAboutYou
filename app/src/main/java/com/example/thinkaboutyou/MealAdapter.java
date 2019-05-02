package com.example.thinkaboutyou;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MealAdapter extends ArrayAdapter {

    private List<Meal> mealList;
    private Context mContext;
    private int layoutid;
    public MealAdapter(@NonNull Context context, List<Meal> list) {
        super(context, 0 , list);
        mContext = context;
        mealList= list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.mealview,parent,false);

        Meal currentNote = mealList.get(position);

        TextView name =  (TextView) listItem.findViewById(R.id.mealViewName);
        name.setText(currentNote.getMeal()+"");


        TextView kcal = (TextView) listItem.findViewById(R.id.mealViewKcal);
        kcal.setText(currentNote.getKcal()+"");

        return listItem;
    }
}
