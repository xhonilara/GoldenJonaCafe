package com.example.xhonilara.goldenjonacafe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by xhonilara on 04/05/17.
 */

public class RateUsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton BTN = (ImageButton) findViewById(R.id.imageButtonRateUs);
        BTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://www.tripadvisor.it/Restaurant_Review-g664212-d10292380-Reviews-Golden_Jona_Cafe-Sesto_Calende_Lake_Maggiore_Lombardy.html"));
                startActivity(myWebLink);
            }
        });
    }
}
