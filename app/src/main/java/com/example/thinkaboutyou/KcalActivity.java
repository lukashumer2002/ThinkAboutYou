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
                    case R.id.action_recents:
                        Toast.makeText(KcalActivity.this, "Recents", Toast.LENGTH_SHORT).show();
                        Intent intentKcal = new Intent(KcalActivity.this, MainActivity.class);
                        startActivity(intentKcal);
                        break;
                    case R.id.action_favorites:

                        Intent intentWo = new Intent(KcalActivity.this, WorkoutActivity.class);
                        startActivity(intentWo);
                        Toast.makeText(KcalActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_nearby:
                        Intent intentRun = new Intent(KcalActivity.this, KcalActivity.class);
                        startActivity(intentRun);
                        Toast.makeText(KcalActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
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
