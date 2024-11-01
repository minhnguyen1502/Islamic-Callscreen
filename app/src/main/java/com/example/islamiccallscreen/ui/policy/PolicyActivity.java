package com.example.islamiccallscreen.ui.policy;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.islamiccallscreen.R;
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
        binding.viewTop.tvToolBar.setText(getString(R.string.privacy_policy));

        binding.viewTop.ivCheck.setVisibility(View.INVISIBLE);

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl(linkPolicy);
    }

    @Override
    public void bindView() {
        binding.viewTop.ivBack.setOnClickListener(v -> onBack());
    }

    @Override
    public void onBack() {
        finishThisActivity();
    }
}
