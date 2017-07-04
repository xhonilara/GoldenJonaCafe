package com.example.xhonilara.goldenjonacafe;

import android.content.ClipData;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

/**
 * Created by xhonilara on 19/05/17.
 */

public class AddEventsActivity extends AppCompatActivity {

private DatabaseReference mDatabase;
    private final static String TAG = "AddEventsActivity";
    private EditText text, text1, text2;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();




        text = (EditText) findViewById(R.id.insertEvent);
        text1 = (EditText) findViewById(R.id.insertDate);
        text2 = (EditText) findViewById(R.id.insertDesc);
        button = (Button) findViewById(R.id.addEventButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Date gc = new Date();
                Event event = new Event(text.getText().toString(),text1.getText().toString(),text2.getText().toString());


                mDatabase.child("events").child(String.valueOf(gc.getTime())).setValue(event);
                text.setText("");
                text1.setText("");
                text2.setText("");


    }
        });

    }

}