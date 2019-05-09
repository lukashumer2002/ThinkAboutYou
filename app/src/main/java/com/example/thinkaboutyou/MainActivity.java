package com.example.thinkaboutyou;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //mAuth = FirebaseAuth.getInstance();
        View viewAddnewMeal = View.inflate(MainActivity.this, R.layout.addnewmeal, null);
        //View inflater = View.inflate(MainActivity.this, R.layout.kcaltooloverview, null);
        View viewLogin = View.inflate(MainActivity.this, R.layout.login, null);
        setContentView(R.layout.navigation);
        email = viewLogin.findViewById(R.id.EditTextUsername);
        passwort = viewLogin.findViewById(R.id.EditTextPasswort);
        buttonLogin = viewLogin.findViewById(R.id.LoginButtonSubmit);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();

                            break;
                        case R.id.action_kcal:
                            Intent intentKcal = new Intent(MainActivity.this,KcalActivity.class);
                            startActivity(intentKcal);
                            Toast.makeText(MainActivity.this, "kcalzähler", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.action_workout:
                            Intent intentWo = new Intent(MainActivity.this, WorkoutActivity.class);
                            startActivity(intentWo);
                            Toast.makeText(MainActivity.this, "workout tool", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.action_run:
                            Intent intentRun = new Intent(MainActivity.this, RunActivity.class);
                            startActivity(intentRun);
                            Toast.makeText(MainActivity.this, "running tool", Toast.LENGTH_SHORT).show();
                            break;

                    }
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