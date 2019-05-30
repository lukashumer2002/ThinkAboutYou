package com.example.thinkaboutyou;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutActivity extends Fragment {
    private TextView mTextMessage;
    ShowWorkouts_Adapter showWorkouts_adapter;
    List<Workouts> WOlist;
    ListView WOlistView;
    FloatingActionButton WOfab;
    TextView countdown;
    private CountDownTimer countDownTimer;
    Button play;
    private long time;
    private boolean timerunning;
    TextView pause;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_workout,container,false);
        WOlist = new ArrayList<>();
        WOlistView = view.findViewById(R.id.WOListView);
        countdown = view.findViewById(R.id.textview_countdown);
        play = view.findViewById(R.id.buttonplay);
        pause = view.findViewById(R.id.buttonpause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
        WOfab = view.findViewById(R.id.WOfloatingActionButton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        WOfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent()
            }
        });
        return view;
    }

    public void setAdapter()
    {
        showWorkouts_adapter = new ShowWorkouts_Adapter(getContext(),WOlist);

    }


    public void startTimer()
    {
        countDownTimer = new CountDownTimer(time, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                time=millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        timerunning=true;
    }


    public void updateTimer()
    {
        int min = (int) time/60000;
        int sec = (int) time%60000/1000;

        String abc;

        String timeLeftText = ""+min;
        timeLeftText+=":";
        if(sec<10) timeLeftText+="+";
        timeLeftText+=sec;

        countdown.setText(timeLeftText);
    }

    public void stopTimer()
    {
        countDownTimer.cancel();
        timerunning = false;
    }


}
