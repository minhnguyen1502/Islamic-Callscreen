package com.example.islamiccallscreen.ui.main;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.islamiccallscreen.ui.fragment.CallScreenFragment;
import com.example.islamiccallscreen.ui.fragment.PrayerFragment;
import com.example.islamiccallscreen.ui.fragment.QiblaFragment;
import com.example.islamiccallscreen.ui.fragment.WallpaperFragment;

import java.util.ArrayList;
import java.util.List;


public class MainAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public MainAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        fragmentList.add(new WallpaperFragment());
        fragmentList.add(new CallScreenFragment());
        fragmentList.add(new PrayerFragment());
        fragmentList.add(new QiblaFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
