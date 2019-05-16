package com.example.thinkaboutyou;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.util.zip.Inflater;

public class KcalActivity extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("LOG", "FAB:success");

        View inflater1 = inflater.inflate(R.layout.activity_kcal,container,false);
        listMeals = new ArrayList<>();
        searchViewKcal = inflater1.findViewById(R.id.KcalToolSearchView);
        floatingActionButtonKcal = (FloatingActionButton) inflater1.findViewById(R.id.KcalToolFabAdd);
        mTextMessage = inflater1.findViewById(R.id.message);
        listViewKcal = inflater1.findViewById(R.id.KcalToolListView);


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
                Log.d("LOG", "FAB:success");
                dialogaddnewMeal(View.inflate(getActivity(), R.layout.addnewmeal, null));
                setAdapter(listMeals);
            }
        });

        return inflater1;
    }


    public void dialogaddnewMeal(View view) {
        alert = new AlertDialog.Builder(getActivity());
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
                String name = addNewMealName.getText().toString().trim();
                String kcal = addNewMealKcal.getText().toString().trim();

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
        ad = new MealAdapter(getActivity(), list);
        listViewKcal.setAdapter(ad);
    }

    public List<Meal> filter(List<Meal> list, String txt)
    {
        List<Meal> currentList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMeal().contains(txt.trim()))
            {
                currentList.add(list.get(i));
            }
        }
        ad = new MealAdapter(getActivity(),currentList);
        listViewKcal.setAdapter(ad);
        return currentList;
    }

}
