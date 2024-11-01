package com.example.islamiccallscreen.ui.setting;

import android.content.Intent;

import com.ziplock.lockscreen.zipperlock.BuildConfig;
import com.ziplock.lockscreen.zipperlock.R;
import com.ziplock.lockscreen.zipperlock.base.BaseActivity;
import com.ziplock.lockscreen.zipperlock.databinding.ActivityAboutBinding;
import com.ziplock.lockscreen.zipperlock.ui.policy.PolicyActivity;
import com.ziplock.lockscreen.zipperlock.util.EventTracking;

public class AboutActivity extends BaseActivity<ActivityAboutBinding> {
    @Override
    public ActivityAboutBinding getBinding() {
        return ActivityAboutBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        binding.txt.setText(getString(R.string.version) + " " + BuildConfig.VERSION_NAME);

        binding.ivBack.setOnClickListener(v -> onBack());

        binding.tvPolicy.setOnClickListener(v -> {
            EventTracking.logEvent(this, "setting_policy_click");
            startActivity(new Intent(AboutActivity.this, PolicyActivity.class));
        });
    }

    @Override
    public void bindView() {

    }

    @Override
    public void onBack() {
        finish();
    }
}
