package com.example.thinkaboutyou;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ShowWorkouts_Adapter extends ArrayAdapter {

    private Context mContext;
    private int layoutid;
    private List<Workouts> workoutList;
    public ShowWorkouts_Adapter(@NonNull Context context, List<Workouts> list) {
        super(context,0,list);
        mContext = context;
        workoutList= list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.mealview,parent,false);

        Workouts currentWo = workoutList.get(position);

//        TextView name =  (TextView) listItem.findViewById(R.id.mealViewName);
//        name.setText(currentWo.getMeal()+"");
//
//
//        TextView kcal = (TextView) listItem.findViewById(R.id.mealViewKcal);
//        kcal.setText(currentWo.getKcal()+"");

        return listItem;
    }
}

