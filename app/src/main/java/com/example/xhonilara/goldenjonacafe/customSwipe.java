package com.example.xhonilara.goldenjonacafe;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xhonilara on 08/05/17.
 */

    public class customSwipe extends PagerAdapter {



    private int [] imageResources = {R.drawable.menu_1, R.drawable.menu_2, R.drawable.menu_3, R.drawable.menu_4, R.drawable.menu_5, R.drawable.menu_6, R.drawable.menu_7, R.drawable.menu_8, R.drawable.menu_9};
    private Context ctx;
    private LayoutInflater layoutInflater;
    public customSwipe(Context c) {

        ctx = c;
    }

    @Override
    public int getCount() {

        return imageResources.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.activity_custom_swipe,container,false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView1);
        TextView textView = (TextView) itemView.findViewById(R.id.text_view_menu);
        imageView.setImageResource(imageResources[position]);
        String prova = "Page "+position;
        if (position!=0){
            textView.setVisibility(View.VISIBLE);
            textView.setText(prova);
        } else {
            textView.setVisibility(View.GONE);
        }


        container.addView(itemView);
        return itemView;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {


    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
}

