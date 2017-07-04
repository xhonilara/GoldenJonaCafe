package com.example.xhonilara.goldenjonacafe;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by xhonilara on 04/05/17.
 */

public class InfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

    }

    public void process(View view) {

        if (view.getId() == R.id.buttonGPS) {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=Golden Jona Cafe,+Viale Italia+Sesto Calende+VA+Italy");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }

        if (view.getId() == R.id.buttonX) {
            String phone = "+393342981951";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }

        if (view.getId() == R.id.buttonJ) {
            String phone = "+393487867623";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }



    }
}
