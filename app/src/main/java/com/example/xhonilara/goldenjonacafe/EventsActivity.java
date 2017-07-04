package com.example.xhonilara.goldenjonacafe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xhonilara.goldenjonacafe.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by xhonilara on 04/05/17.
 */

public class EventsActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        // get the references to the views
        final ListView listView = (ListView) findViewById(R.id.list_event);
// the list containing the todo items
        final ArrayList<Event> todoItems = new ArrayList<Event>();
// the adapter populating the listview
        final ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this,
                android.R.layout.simple_list_item_1, todoItems);
        listView.setAdapter(adapter);
        firebaseDatabase = FirebaseDatabase.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase = firebaseDatabase.getReference();
        listView.setBackgroundColor(Color.WHITE);
        mDatabase.child("events").orderByValue().limitToLast(15).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Event e = new Event();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    Log.d("prova", data.toString());
                    ArrayList<String> arrayList = new ArrayList<String>();
                    for (DataSnapshot data2 : data.getChildren()){

                        if (data2.getKey().equalsIgnoreCase("name")) {
                            Log.d("entrato","aa");
                            e.setName(data2.getValue().toString());
                        } else if (data2.getKey().equalsIgnoreCase("date")) {
                            Log.d("entrato","bb");
                            e.setDate(data2.getValue().toString());
                        } else if (data2.getKey().equalsIgnoreCase("description")){
                            e.setDescription(data2.getValue().toString());
                        }

                    }
                    adapter.add(e);
                    e = new Event();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
