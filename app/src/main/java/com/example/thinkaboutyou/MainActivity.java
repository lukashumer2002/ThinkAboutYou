package com.example.thinkaboutyou;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText email;
    EditText passwort;
    Button buttonLogin;
    private BottomNavigationView navigationView123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mAuth = FirebaseAuth.getInstance();
        View viewAddnewMeal = View.inflate(MainActivity.this, R.layout.addnewmeal, null);
        //View inflater = View.inflate(MainActivity.this, R.layout.kcaltooloverview, null);
        View viewLogin = View.inflate(MainActivity.this, R.layout.login, null);


//        fm.beginTransaction().add(R.id.action_run, fragment3, "3").hide(fragment3).commit();
//        fm.beginTransaction().add(R.id.action_workout, fragment2, "2").hide(fragment2).commit();
//        fm.beginTransaction().add(R.id.action_run,fragment1, "1").hide(fragment2).commit();
//        fm.beginTransaction().add(R.id.action_home,fragment0,"1").commit();

        email = viewLogin.findViewById(R.id.EditTextUsername);
        passwort = viewLogin.findViewById(R.id.EditTextPasswort);
        navigationView123 = findViewById(R.id.bottom_navigation);
        buttonLogin = viewLogin.findViewById(R.id.LoginButtonSubmit);

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