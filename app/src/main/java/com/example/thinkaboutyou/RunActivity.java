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

public class RunActivity extends Fragment {
    private TextView mTextMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_run,container,false);
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.action_home:
//                    Intent intenthome = new Intent(RunActivity.this, MainActivity.class);
//                    startActivity(intenthome);
//                    Toast.makeText(RunActivity.this, "home", Toast.LENGTH_SHORT).show();
//
//                    break;
//                case R.id.action_kcal:
//                    Intent intentKcal = new Intent(RunActivity.this, KcalActivity.class);
//                    startActivity(intentKcal);
//                    Toast.makeText(RunActivity.this, "kcalzähler", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.action_workout:
//                    Intent intentWo = new Intent(RunActivity.this, WorkoutActivity.class);
//                    startActivity(intentWo);
//                    Toast.makeText(RunActivity.this, "workout tool", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.action_run:
//                    Toast.makeText(RunActivity.this, "running tool", Toast.LENGTH_SHORT).show();
//                    break;
//
//            }
//            return true;
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_run);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//    }
}

