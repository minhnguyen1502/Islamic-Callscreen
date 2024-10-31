package com.example.islamiccallscreen.ui.b;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.islamiccallscreen.base.BaseFragment;
import com.example.islamiccallscreen.databinding.FragmentBBinding;

public class BFragment extends BaseFragment<FragmentBBinding> {


    @Override
    public FragmentBBinding setBinding(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return FragmentBBinding.inflate(inflater,container,false);
    }

    @Override
    public void initView() {

    }

    @Override
    public void bindView() {
        binding.rlOnclickFb.setOnClickListener(view -> onClickAnimation(view));
    }
}
