package com.example.thinkaboutyou;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KcalActivity extends AppCompatActivity {
    private TextView mTextMessage;
    AlertDialog dialog;
    AlertDialog.Builder alert;

    EditText email;
    EditText passwort;
    Button buttonLogin;
    SearchView searchViewKcal;
    ListView listViewKcal;
    FloatingActionButton floatingActionButtonKcal;
    EditText addNewMealName;
    EditText addNewMealKcal;
    List<Meal> listMeals;
    MealAdapter ad;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcal);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        listMeals = new ArrayList<>();
        searchViewKcal = findViewById(R.id.KcalToolSearchView);
        floatingActionButtonKcal = findViewById(R.id.KcalToolFabAdd);
        mTextMessage = findViewById(R.id.message);
        listViewKcal = findViewById(R.id.KcalToolListView);




        searchViewKcal.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(listMeals, newText);
                return false;
            }
        });

        floatingActionButtonKcal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogaddnewMeal(View.inflate(KcalActivity.this, R.layout.addnewmeal, null));
            setAdapter(listMeals);
        }
    });
    }

    public void dialogaddnewMeal(View view) {
        alert = new AlertDialog.Builder(this);
        alert.setView(view).setCancelable(false);

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        dialog = alert.create();
        addNewMealKcal = view.findViewById(R.id.addnewmealKcal);
        addNewMealName = view.findViewById(R.id.addnewmealName);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = addNewMealName.getText().toString();
                String kcal = addNewMealKcal.getText().toString();

                Meal meal = new Meal(name, Integer.valueOf(kcal));
                listMeals.add(meal);

                dialog.dismiss();
            }
        });

        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public void setAdapter(List<Meal> list)
    {
        ad = new MealAdapter(this, list);
        listViewKcal.setAdapter(ad);
    }

    public List<Meal> filter(List<Meal> list, String txt)
    {
        List<Meal> currentList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMeal().contains(txt))
            {
                currentList.add(list.get(i));
            }
        }
        ad = new MealAdapter(this,currentList);
        listViewKcal.setAdapter(ad);
        return currentList;
    }

}
