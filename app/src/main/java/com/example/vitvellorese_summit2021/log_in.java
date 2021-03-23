package com.example.vitvellorese_summit2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;

public class log_in extends AppCompatActivity {
    Animation side1,side2;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        side1= AnimationUtils.loadAnimation(this,R.anim.side1);
        side2= AnimationUtils.loadAnimation(this,R.anim.side2);
        viewPager=findViewById(R.id.viewPager2);
        tabLayout=findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Log in"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        if(SaveSharedPreference.getUserName(this).length() == 0)
        {

        }
        else
        {
            startActivity(new Intent(this,main_home.class));
        }
        tab_layout_switch adapter=new tab_layout_switch(getSupportFragmentManager(),getLifecycle());
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });



        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                log_in.setVisibility(View.VISIBLE);
                startActivity(new Intent(getApplicationContext(),main_home.class));
            }
        },1600);*/
    }
}