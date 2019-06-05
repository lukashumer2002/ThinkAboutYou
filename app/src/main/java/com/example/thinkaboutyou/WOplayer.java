package com.example.thinkaboutyou;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class WOplayer extends AppCompatActivity {
    private static long currenttime;
    TextView name;
    Button next;
    Button stop;
    ImageView imageview;
    TextView textViewWdh;
    TextView countdown;
    private CountDownTimer countDownTimer;
    Button play;

    private boolean timerunning;
    TextView pause;
    List<Workouts> TRAINlist;
    Workouts currentWO;
    int counter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.woplayeractivity);
        timerunning=false;
        counter = 0;
        WorkoutActivity workoutActivity = new WorkoutActivity();
        TRAINlist=workoutActivity.getCurrentWOList();
        countdown =findViewById(R.id.RUNtextview_time);
        next = findViewById(R.id.buttonnext);
        name=findViewById(R.id.RUNtextview_steps);
        stop=findViewById(R.id.buttonstop);
        imageview=findViewById(R.id.RUNimageview);
        textViewWdh = findViewById(R.id.textview_descritption);
        play =findViewById(R.id.RUNbuttonplay);
        pause = findViewById(R.id.RUNbuttonpause);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < TRAINlist.size(); i++) {
                    currentWO=TRAINlist.get(i);
                    currenttime=currentWO.getTime();
                    name.setText(currentWO.getName());
                    play(currentWO,currenttime);
                }
            }
        });


        //nextButtton();
    }




    public void startTimer()
    {
        countDownTimer = new CountDownTimer(currenttime, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                currenttime=millisUntilFinished;
                updateTimer();

                nextButtton();

                if(millisUntilFinished==0)
                {
                    if(TRAINlist.size()==counter)
                    {
                        //fertig
                    }
                    else {
                        mochMoiPause();
                        counter++;
                        currentWO=TRAINlist.get(counter);
                        currenttime=currentWO.getTime();
                        name.setText(currentWO.getName());
                        play(currentWO,currenttime);
                    }
                }

                pause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stopTimer();
                    }
                });

                nextButtton();
            }

            @Override
            public void onFinish() {

            }



        }.start();
        timerunning=true;
    }


    public void updateTimer()
    {
        int min = (int) currenttime/60000;
        int sec = (int) currenttime%60000/1000;

        String abc;

        String timeLeftText = ""+min;
        timeLeftText+=":";
        if(sec<10) timeLeftText+="0";
        timeLeftText+=sec;

        countdown.setText(timeLeftText);
    }

    public void stopTimer()
    {
        countDownTimer.cancel();
        timerunning = false;
    }

    public void play(Workouts myWorkout, long currenttime)
    {
        System.out.println("III Daten: "+myWorkout.toString());



            if (currenttime>0) {

                startTimer();
                System.out.println("III Time: "+currenttime);
            }
            else if (currenttime<0)
            {
                countdown.setText("");
            }

            if(myWorkout.getImagePath()!=null)
            {
                imageview.setImageURI(Uri.parse(myWorkout.getImagePath()));
            }
            if(myWorkout.getWdh()>0)
            {
                textViewWdh.setText(myWorkout.getWdh()+" WDH");
            }
            else if(myWorkout.getWdh()<0)
            {
                textViewWdh.setText("");
            }

            nextButtton();
    }

    public void nextButtton()
    {

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopTimer();
                if(TRAINlist.size()==counter)
                {
                    //fertig
                }
                else {
                    mochMoiPause();
                    counter++;
                    currentWO=TRAINlist.get(counter);
                    currenttime=currentWO.getTime();


                }
            }
        });
    }

    public void mochMoiPause()
    {
        Workouts workouts = new Workouts("Pause",-1,null,20);
        name.setText(workouts.getName());
        play(workouts,20000);
    }



}
