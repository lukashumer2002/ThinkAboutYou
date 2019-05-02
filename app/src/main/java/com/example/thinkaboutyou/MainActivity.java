package com.example.thinkaboutyou;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listMeals = new ArrayList<>();
        //mAuth = FirebaseAuth.getInstance();
        View viewAddnewMeal = View.inflate(MainActivity.this, R.layout.addnewmeal, null);
        View inflater = View.inflate(MainActivity.this, R.layout.kcaltooloverview, null);
        View viewLogin = View.inflate(MainActivity.this, R.layout.login, null);
        setContentView(R.layout.kcaltooloverview);
        searchViewKcal = inflater.findViewById(R.id.KcalToolSearchView);
        floatingActionButtonKcal = findViewById(R.id.KcalToolFabAdd);
        listViewKcal = findViewById(R.id.KcalToolListView);
        email = viewLogin.findViewById(R.id.EditTextUsername);
        passwort = viewLogin.findViewById(R.id.EditTextPasswort);
        buttonLogin = viewLogin.findViewById(R.id.LoginButtonSubmit);

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
                dialogaddnewMeal(View.inflate(MainActivity.this, R.layout.addnewmeal, null));
                setAdapter(listMeals);
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = email.getText().toString();
                String p = passwort.getText().toString();

                if (m.contains("signup/")) {
                    signUpUser(m, p);
                } else {
                    signInUser(m, p);
                }
            }
        });
    }

    private void signInUser(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            signUpUser(email, password);
                        }
                    }
                });
    }

    private void signUpUser(String email, String password) {

        String[] arr = email.split("/");
        mAuth.createUserWithEmailAndPassword(arr[1], password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LOG", "createUserWithEmail:failure", task.getException());
                        }
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