package com.example.thinkaboutyou;

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
import android.widget.TextView;

public class WOplayer extends Fragment {

    TextView countdown;
    private CountDownTimer countDownTimer;
    Button play;
    private long time;
    private boolean timerunning;
    TextView pause;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.woplayeractivity,container,false);
        timerunning=false;
        countdown = view.findViewById(R.id.textview_countdown);
        play = view.findViewById(R.id.buttonplay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        pause = view.findViewById(R.id.buttonpause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        return view;
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
