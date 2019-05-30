package com.example.thinkaboutyou;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WOplayer extends AppCompatActivity {

    TextView countdown;
    private CountDownTimer countDownTimer;
    Button play;
    private long time;
    private boolean timerunning;
    TextView pause;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.woplayeractivity);
        timerunning=false;
        countdown = findViewById(R.id.textview_countdown);
        play = findViewById(R.id.buttonplay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        pause = findViewById(R.id.buttonpause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });


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
