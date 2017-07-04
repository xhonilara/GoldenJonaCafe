package com.example.xhonilara.goldenjonacafe;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.R.string.ok;

/**
 * Created by xhonilara on 04/05/17.
 */

public class LoginActivity extends AppCompatActivity {



    private String password;
    private String email;
    private final static String TAG="LoginActivity";
    protected EditText emailEditText;
    protected EditText passwordEditText;
    protected Button loginButton;
    protected TextView signUpTextView;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth mAuth;
    private CheckBox remindMeCheckBox;
    private Boolean remindMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()).getBoolean("remindMe", false))
            goToHomePage();
        else {
            setContentView(R.layout.activity_login);
            mAuth = FirebaseAuth.getInstance();
            mFirebaseAuth = FirebaseAuth.getInstance();

            remindMeCheckBox = (CheckBox) findViewById(R.id.checkBox);
            signUpTextView = (TextView) findViewById(R.id.signUpText);
            emailEditText = (EditText) findViewById(R.id.emailField);
            passwordEditText = (EditText) findViewById(R.id.passwordField);
            loginButton = (Button) findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
                    email = email.trim();
                    password = password.trim();

                    if (email.isEmpty() || password.isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage(R.string.login_error_message)
                                .setTitle(R.string.login_error_title)
                                .setPositiveButton(ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            if (remindMeCheckBox.isChecked()) {
                                                PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit()
                                                        .putBoolean("remindMe", true)
                                                        .putString("PREF_EMAIL", emailEditText.getText().toString())
                                                        .putString("PREF_PASS", passwordEditText.getText().toString())
                                                        .commit();
                                            }

                                            if(( emailEditText.getText().toString().equals(AppStat.ADMIN_EMAIL_1) && passwordEditText.getText().toString().equals(AppStat.ADMIN_PSW_1))
                                                    || ( emailEditText.getText().toString().equals(AppStat.ADMIN_EMAIL_2) && passwordEditText.getText().toString().equals(AppStat.ADMIN_PSW_2) ) )
                                            {
                                                AppStat.setIsAdmin(true);
                                            } else {
                                                AppStat.setIsAdmin(false);
                                            }

                                            goToHomePage();

                                        } else {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                            builder.setMessage(task.getException().getMessage())
                                                    .setTitle(R.string.login_error_title)
                                                    .setPositiveButton(ok, null);
                                            AlertDialog dialog = builder.create();
                                            dialog.show();
                                        }
                                    }
                                });
                    }
                }
            });
        }

    }



    public void onClickLogin ( View v) {

        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, " auth failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void goToHomePage(){
        startActivity(new Intent(this.getApplicationContext(), HomePage.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }

    public void onClickToRegister ( View v){

        startActivity(new Intent( v.getContext(), SignUpActivity.class ));
    }


}
