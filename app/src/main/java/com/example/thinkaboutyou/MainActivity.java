package com.example.thinkaboutyou;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText email;
    EditText passwort;
    Button buttonLogin;
    private BottomNavigationView navigationView123;
    private TextView mTextMessage;

    AlertDialog dialog;
    AlertDialog.Builder alert;

    EditText email1;
    EditText passwort1;
    Button buttonLogin1;
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
        setContentView(R.layout.activity_main);
        //mAuth = FirebaseAuth.getInstance();
        View viewAddnewMeal = View.inflate(MainActivity.this, R.layout.addnewmeal, null);
        View inflaterKCAL = View.inflate(MainActivity.this, R.layout.activity_kcal, null);
        View viewLogin = View.inflate(MainActivity.this, R.layout.login, null);
        email = viewLogin.findViewById(R.id.EditTextUsername);
        passwort = viewLogin.findViewById(R.id.EditTextPasswort);
        navigationView123 = findViewById(R.id.bottom_navigation);
        buttonLogin = viewLogin.findViewById(R.id.LoginButtonSubmit);

        //KCALACTIVIZY-------------------

//        mTextMessage = inflaterKCAL.findViewById(R.id.message);
//        listMeals = new ArrayList<>();
//        searchViewKcal = inflaterKCAL.findViewById(R.id.KcalToolSearchView);
//        floatingActionButtonKcal = inflaterKCAL.findViewById(R.id.KcalToolFabAdd);
//        mTextMessage = inflaterKCAL.findViewById(R.id.message);
//        listViewKcal = inflaterKCAL.findViewById(R.id.KcalToolListView);

        //--------------------------------

        navigationView123.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFra = null;
                switch (item.getItemId()) {

                    case R.id.action_home:
                        selectedFra = new HomeFragment();
                        Log.d("LOG", "Homeactivity:success");

                        break;

                    case R.id.action_kcal:
                        selectedFra = new KcalActivity();
                        Log.d("LOG", "Kcalactivity:success");

                        break;
                    case R.id.action_workout:
                        selectedFra = new WorkoutActivity();


                        Log.d("LOG", "Workoutactivity:success");

                        break;
                    case R.id.action_run:
                        selectedFra = new RunActivity();

                        Log.d("LOG", "RunActivity:success");
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer, selectedFra).commit();
                return true;
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


}