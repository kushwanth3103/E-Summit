package com.example.vitvellorese_summit2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class main_home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2_mains;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bottomNavigationView=findViewById(R.id.navigation);
        frameLayout=findViewById(R.id.framelayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new home_fragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                 Class fragmentClass;

                switch(item.getItemId())
                {
                    case R.id.speakers:
                        fragmentClass=speakers.class;
                        break;
                    case R.id.events:
                        fragmentClass=events_fragment.class;
                        break;
                    case R.id.profile:
                        fragmentClass=profile_fragment.class;
                        break;
                    default:
                        fragmentClass=home_fragment.class;
                }
                try {
                    fragment=(Fragment)fragmentClass.newInstance();

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager=getSupportFragmentManager();
                if (fragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment).commit();
                }
                return true;
            }
        });

    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}