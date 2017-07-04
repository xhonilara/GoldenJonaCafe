package com.example.xhonilara.goldenjonacafe;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by xhonilara on 04/05/17.
 */

public class HomePage extends AppCompatActivity {


    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final static String TAG="HomePage";


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        mAuthListener = new FirebaseAuth.AuthStateListener(){
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }

        }
    };



        mAuth = FirebaseAuth.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        if (mFirebaseUser == null) {
            loadLoginView();
        } else {
            mUserId = mFirebaseUser.getUid();

        }

    }

    private void loadLoginView() {
        PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext())
                .edit().putBoolean("remindMe", false)
                .putString("PREF_EMAIL", "")
                .putString("PREF_PASS", "")
                .commit();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        if (AppStat.isAdmin()){

            getMenuInflater().inflate(R.menu.menu_admin, menu);

        }
        else {
            getMenuInflater().inflate(R.menu.menu_main, menu);

        }
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            loadLoginView();
        }
        if (id == R.id.action_add_events) {
            Intent intent = new Intent(this, AddEventsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }





    public void onClickImageMenu( View v ){
        startActivity(new Intent( v.getContext(), MenuActivity.class ));

    }

    public void onClickImageInfo( View v) {
        startActivity(new Intent( v.getContext(), InfoActivity.class ));

    }

    public void onClickImageEvents( View v){
        startActivity(new Intent(v.getContext(), EventsActivity.class ));

    }

    public void onClickImageRateUs( View v) {
        startActivity( new Intent(v.getContext(), RateUsActivity.class ));

    }


}
