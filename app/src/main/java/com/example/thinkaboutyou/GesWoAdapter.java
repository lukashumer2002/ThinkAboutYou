package com.example.thinkaboutyou;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GesWoAdapter extends ArrayAdapter {
    private Context mContext;
    private int layoutid;
    private List<GesammtWO> geswo;
    public GesWoAdapter(@NonNull Context context, List<GesammtWO> list) {
        super(context,0,list);
        mContext = context;
        geswo= list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        AssetManager mngr = getContext().getAssets();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row =  inflater.inflate(R.layout.showworkouts,null,false);




        GesammtWO gesammtWO = geswo.get(position);

        TextView description = (TextView) row.findViewById(R.id.WOdescription);
        ImageView image = (ImageView) row.findViewById(R.id.WOimageView);

        description.setText(gesammtWO.getName());



            InputStream is = null;
            try {
                is = mngr.open("sports123.jpg");
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


            //image.setImageURI(Uri.parse(workouts.getImagePath()));

        return row;
    }
}