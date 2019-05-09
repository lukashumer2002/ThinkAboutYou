package com.example.thinkaboutyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_recents:
                            Toast.makeText(WorkoutActivity.this, "Recents", Toast.LENGTH_SHORT).show();
                            Intent intentKcal = new Intent(WorkoutActivity.this,KcalActivity.class);
                            startActivity(intentKcal);
                            break;
                        case R.id.action_favorites:

                            Intent intentWo = new Intent(WorkoutActivity.this,WorkoutActivity.class);
                            startActivity(intentWo);
                            Toast.makeText(WorkoutActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.action_nearby:
                            Intent intentRun = new Intent(WorkoutActivity.this, KcalActivity.class);
                            startActivity(intentRun);
                            Toast.makeText(WorkoutActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    return true;
            }

        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        BottomNavigationView navView = findViewById(R.id.navigationView);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
