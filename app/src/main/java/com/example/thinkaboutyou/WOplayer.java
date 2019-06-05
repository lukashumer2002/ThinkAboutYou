package com.example.thinkaboutyou;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Trace;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WOplayer extends AppCompatActivity {
    private static long currenttime;
    TextView name;
    Button next;
    AlertDialog.Builder alert2;
    AlertDialog dialog2;
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
        timerunning = false;
        counter = 0;
        WorkoutActivity workoutActivity = new WorkoutActivity();
        TRAINlist = workoutActivity.getCurrentWOList();
        countdown = findViewById(R.id.RUNtextview_time);
        next = findViewById(R.id.buttonnext);
        name = findViewById(R.id.RUNtextview_steps);
        stop = findViewById(R.id.buttonstop);
        imageview = findViewById(R.id.RUNimageview);
        textViewWdh = findViewById(R.id.textview_descritption);
        play = findViewById(R.id.RUNbuttonplay);
        //currentWO=TRAINlist.get(0);
        System.out.println("III: " + TRAINlist.toString());
        //name.setText(currentWO.getName());
        pause = findViewById(R.id.RUNbuttonpause);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < TRAINlist.size(); i++) {
                    currentWO = TRAINlist.get(i);
                    currenttime = currentWO.getTime();
                    System.out.println("currenttime: " + currenttime);
                    System.out.println("currentname" + currentWO.getName());
                    name.setText(currentWO.getName());
                    if (currentWO.getImagePath() != null) {
                        setImage(currentWO.getImagePath());
                    }
                    else
                    {
                        setImage("sports.jpg");
                    }

                    play(currentWO, currenttime);
                }
                Toast.makeText(getApplicationContext(), "Du hast dein Workout abgeschlossen!!!", Toast.LENGTH_LONG).show();
                switchbackToFragment();
            }
        });



        stopButton();


        //nextButtton();
    }


    public void startTimer() {
        countDownTimer = new CountDownTimer(currenttime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currenttime = millisUntilFinished;
                updateTimer();

                nextButtton();

                if (millisUntilFinished == 0) {
                    nextButtton();
                }

                //pauseButton();

                //nextButtton();
            }

            @Override
            public void onFinish() {

            }


        }.start();
        timerunning = true;
    }


    public void updateTimer() {
        int min = (int) currenttime / 60000;
        int sec = (int) currenttime % 60000 / 1000;

        String abc;

        String timeLeftText = "" + min;
        timeLeftText += ":";
        if (sec < 10) timeLeftText += "0";
        timeLeftText += sec;

        countdown.setText(timeLeftText);
    }

    public void stopTimer() {
        countDownTimer.cancel();
        currenttime = 0;
        timerunning = false;
    }


    public void play(Workouts myWorkout, long currenttime) {
        System.out.println("III Daten: " + myWorkout.toString());

        if (currenttime > 0) {

            startTimer();
            System.out.println("III Time: " + currenttime);
        } else if (currenttime < 0) {
            countdown.setText("");

        }

        if (myWorkout.getWdh() > 0) {
            textViewWdh.setText(myWorkout.getWdh() + " WDH");
        } else if (myWorkout.getWdh() < 0) {
            textViewWdh.setText("");
        }
        pauseButton();
        nextButtton();
        stopButton();
    }

    public void nextButtton() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                if (TRAINlist.size() == counter) {
                    //fertig



                } else {
                    //mochMoiPause();
                    counter++;
                    currentWO = TRAINlist.get(counter);
                    currenttime = currentWO.getTime();


                }
            }
        });
    }

//    public void mochMoiPause()
//    {
//        Workouts workouts = new Workouts("Pause",-1,null,20);
//        name.setText(workouts.getName());
//        play(workouts,20000);
//    }

    public void switchbackToFragment() {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void stopButton() {
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(View.inflate(getApplicationContext(), R.layout.test, null));
            }
        });
    }

    public void pauseButton() {
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
    }

    public void dialog(View view) {
        alert2 = new AlertDialog.Builder(this);
        alert2.setView(view).setCancelable(false);
        TextView tv9 = view.findViewById(R.id.textView9);

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        dialog2 = alert2.create();

        tv9.setText("Möchtest du wirklich abbrechen?");

        alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switchbackToFragment();
            }
        });

        alert2.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert2.show();
    }

    public void setImage(String path) {

        AssetManager mngr = this.getAssets();
        InputStream is = null;
        try {
            is = mngr.open(path);
        } catch (IOException e1) {
            System.out.println("ERROR!!!");
        }

        //Get the texture from the Android resource directory
        //InputStream is = context.getResources().openRawResource(R.drawable.radiocd5);
        Bitmap bitmap = null;
        try {
            //BitmapFactory is an Android graphics utility for images
            bitmap = BitmapFactory.decodeStream(is);
            imageview.setImageBitmap(bitmap);
            System.out.println("DONE");


        } catch (Exception ex) {
            System.out.println("FAILED");
        }

    }
}
