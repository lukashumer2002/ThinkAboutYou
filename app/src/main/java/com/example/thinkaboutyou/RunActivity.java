package com.example.thinkaboutyou;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Stopwatch;

import java.sql.Time;
import java.util.Locale;

import static android.content.Context.SENSOR_SERVICE;

public class RunActivity extends Fragment implements SensorEventListener{


    int TOTALsteps;
    long pauseoffset;
    Chronometer chronometer;
    boolean isRunning;
    private TextView mTextMessage;
    private static final long START_TIME_IN_MILLS = 600000;
    TextView tv_steps;
    private CountDownTimer mCountdownTimer;
    TextView time;
    private SensorManager sensorManager;
    boolean running = false;
    Button TIMEplay;
    Button TIMEpause;
    Button TIMEstop;
    private boolean timerRunning;
    private long timeLeftInMills = START_TIME_IN_MILLS;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_run ,container,false);
        timerRunning=false;
        pauseoffset=0;
        tv_steps = view.findViewById(R.id.RUNtextview_steps);
        tv_steps.setText("0");

        TIMEpause = view.findViewById(R.id.RUNbuttonpause);
        TIMEplay = view.findViewById(R.id.RUNbuttonplay);
        TIMEstop = view.findViewById(R.id.buttonstop);
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        chronometer = view.findViewById(R.id.chronometer);
        TIMEplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometer();
            }
        });

        TIMEpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseChronometer();
                onPause();
            }
        });

        TIMEstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChronometer();
                onResume();
            }
        });
//
//        TIMEplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//               if (!timerRunning)
//               {
//                   startTimer();
//               }
//
//            }
//        });
//
//        TIMEstop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!timerRunning)
//                {
//                    stopTimer();
//                }
//
//            }
//        });
//
//        TIMEstop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!timerRunning)
//                {
//                    stopTimer();
//                    tv_steps.setText("0");
//                }
//
//            }
//        });


        return view;
    }

    public void startChronometer()
    {
        if(!isRunning)
        {
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseoffset);
            chronometer.start();
            isRunning=true;
        }
    }

    public void pauseChronometer()
    {
        if(isRunning)
        {
            chronometer.stop();
            pauseoffset=SystemClock.elapsedRealtime()-chronometer.getBase();
            isRunning=false;
        }
    }

    public void resetChronometer()
    {

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
        isRunning=false;
        pauseoffset=0;

    }


//    public void startTimer()
//    {
//        mCountdownTimer = new CountDownTimer(timeLeftInMills,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                timeLeftInMills=millisUntilFinished;
//                updateTimerText();
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
//        timerRunning=true;
//    }
//
//    public void stopTimer()
//    {
//        mCountdownTimer.cancel();
//        timerRunning=false;
//
//    }
//
//    public void updateTimerText()
//    {
//        int min = (int) timeLeftInMills/1000/60;
//        int sec = (int) timeLeftInMills%1000%60;
//
//        String timeLeftFormat = String.format(Locale.getDefault(),"%02d:%02d",min,sec);
//        time.setText(timeLeftFormat);
//    }
//
    @Override
    public void onResume() {
        super.onResume();
        running=true;
        Sensor countsensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countsensor != null)
        {
            sensorManager.registerListener(this, countsensor,SensorManager.SENSOR_DELAY_UI);

        }else
        {
            Toast.makeText(getContext(),"Sensor not found!", Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void onPause() {
        super.onPause();
        running=false;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running)
        {
            TOTALsteps= (int) event.values[0];
            tv_steps.setText(String.valueOf(event.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

