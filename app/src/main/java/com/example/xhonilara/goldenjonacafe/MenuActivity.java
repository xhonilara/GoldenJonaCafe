package com.example.xhonilara.goldenjonacafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.example.xhonilara.goldenjonacafe.R.styleable.View;


/**
 * Created by xhonilara on 04/05/17.
 */

public class MenuActivity extends AppCompatActivity {


    ViewPager viewPager;
    customSwipe customSwipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        customSwipe = new customSwipe(this);
        viewPager.setAdapter(customSwipe);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
