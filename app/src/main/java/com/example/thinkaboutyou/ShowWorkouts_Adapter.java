package com.example.thinkaboutyou;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row =  inflater.inflate(R.layout.showworkouts,null,false);


        TextView description = (TextView) row.findViewById(R.id.WOdescription);
        ImageView image = (ImageView) row.findViewById(R.id.WOimageView);

        Workouts workouts = workoutList.get(position);

        description.setText(workouts.getName());

        if (workouts.getImagePath()!= null) {
            image.setImageURI(Uri.parse(workouts.getImagePath()));
        }
        return row;
    }
}

