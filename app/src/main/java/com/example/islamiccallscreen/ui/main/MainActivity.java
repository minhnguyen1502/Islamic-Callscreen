package com.example.islamiccallscreen.ui.main;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

import com.example.islamiccallscreen.base.BaseActivity;
import com.example.islamiccallscreen.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    public ActivityMainBinding getBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {

        binding.viewPager.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(mainAdapter);
        binding.viewPager.setUserInputEnabled(false);
    }

    @Override
    public void bindView() {
        binding.btnCallscreen.setOnClickListener(view -> setPage(0));
        binding.btnWallpaper.setOnClickListener(view -> setPage(1));
        binding.btnPrayer.setOnClickListener(view -> setPage(2));
        binding.btnQibla.setOnClickListener(view -> setPage(3));
    }

    @Override
    public void onBack() {
        finishThisActivity();
    }

    public void setPage(int pos) {
        binding.viewPager.setCurrentItem(pos, false);

        switch (pos) {
            case 0:

                break;

            case 1:

                break;
            case 2:

                break;

            case 3:

                break;
        }

    }
}
