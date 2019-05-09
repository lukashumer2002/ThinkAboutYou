package com.example.thinkaboutyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class KcalActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kcaltooloverview);
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(KcalActivity.this, "home", Toast.LENGTH_SHORT).show();

                        Intent intenthome = new Intent(KcalActivity.this,MainActivity.class);
                        startActivity(intenthome);
                        break;
                    case R.id.action_kcal:
                        Toast.makeText(KcalActivity.this, "kcalzähler", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_workout:
                        Intent intentWo = new Intent(KcalActivity.this, WorkoutActivity.class);
                        startActivity(intentWo);
                        Toast.makeText(KcalActivity.this, "workout tool", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_run:
                        Intent intentRun = new Intent(KcalActivity.this, RunActivity.class);
                        startActivity(intentRun);
                        Toast.makeText(KcalActivity.this, "running tool", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        };
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
