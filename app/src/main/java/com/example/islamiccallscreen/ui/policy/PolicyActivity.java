package com.example.islamiccallscreen.ui.policy;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.islamiccallscreen.R;
import com.example.islamiccallscreen.ads.IsNetWork;
import com.example.islamiccallscreen.base.BaseActivity;
import com.example.islamiccallscreen.databinding.ActivityPolicyBinding;

public class PolicyActivity extends BaseActivity<ActivityPolicyBinding> {

    String linkPolicy = "";

    @Override
    public ActivityPolicyBinding getBinding() {
        return ActivityPolicyBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl(linkPolicy);
        if (IsNetWork.haveNetworkConnection(this)) {
            binding.webView.setVisibility(View.VISIBLE);
            binding.ivNoInternet.setVisibility(View.GONE);
            binding.tvNoInternet.setVisibility(View.GONE);

            binding.webView.getSettings().setJavaScriptEnabled(true);
            binding.webView.loadUrl(linkPolicy);
        } else {
            binding.webView.setVisibility(View.GONE);
            binding.ivNoInternet.setVisibility(View.VISIBLE);
            binding.tvNoInternet.setVisibility(View.VISIBLE);

            binding.webView.getSettings().setJavaScriptEnabled(true);
            binding.webView.loadUrl(linkPolicy);
            binding.ivBack.setOnClickListener(v -> onBack());
        }
        binding.ivBack.setOnClickListener(v -> onBack());
    }

    @Override
    public void bindView() {
    }

    @Override
    public void onBack() {
        finish();
    }
}
