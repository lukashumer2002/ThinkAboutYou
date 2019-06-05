package com.example.thinkaboutyou;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static android.content.ContentValues.TAG;

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


        AssetManager mngr = getContext().getAssets();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row =  inflater.inflate(R.layout.showworkouts,null,false);
        

        TextView description = (TextView) row.findViewById(R.id.WOdescription);
        ImageView image = (ImageView) row.findViewById(R.id.WOimageView);

        Workouts workouts = workoutList.get(position);

        description.setText(workouts.getName());

        if (workouts.getImagePath()!= null) {

            InputStream is = null;
            try {
                is = mngr.open(workouts.getImagePath());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            //Get the texture from the Android resource directory
            //InputStream is = context.getResources().openRawResource(R.drawable.radiocd5);
            Bitmap bitmap = null;
            try {
                //BitmapFactory is an Android graphics utility for images
                bitmap = BitmapFactory.decodeStream(is);
                image.setImageBitmap(bitmap);
                System.out.println("DONE");


            }catch (Exception ex)
            {
                System.out.println("FAILED");
            }

//            finally {
//                //Always clear and close
//                try {
//                    is.close();
//                    is = null;
//                } catch (IOException e) {
//                }
//            }


        }
        return row;
    }




}

