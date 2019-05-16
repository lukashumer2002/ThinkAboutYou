package com.example.thinkaboutyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends Fragment {
    private TextView mTextMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_workout,container,false);
    }


//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.action_home:
//                    Toast.makeText(WorkoutActivity.this, "home", Toast.LENGTH_SHORT).show();
//                    Intent intenthome = new Intent(WorkoutActivity.this, MainActivity.class);
//                    startActivity(intenthome);
//                    break;
//                case R.id.action_kcal:
//                    Intent intentKcal = new Intent(WorkoutActivity.this,KcalActivity.class);
//                    startActivity(intentKcal);
//                    Toast.makeText(WorkoutActivity.this, "kcalzähler", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.action_workout:
//
//                    Toast.makeText(WorkoutActivity.this, "workout tool", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.action_run:
//                    Intent intentRun = new Intent(WorkoutActivity.this, RunActivity.class);
//                    startActivity(intentRun);
//                    Toast.makeText(WorkoutActivity.this, "running tool", Toast.LENGTH_SHORT).show();
//                    break;
//
//            }
//            return true;
//        }
//    };

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_workout);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//    }

}
