package com.example.vitvellorese_summit2021;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class tab_layout_switch extends FragmentStateAdapter {

    public tab_layout_switch(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new log_in_fragment();
            case 1:
                return  new register_fragment();
            default:
                return new log_in_fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
