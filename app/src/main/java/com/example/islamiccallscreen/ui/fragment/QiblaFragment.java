package com.example.islamiccallscreen.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islamiccallscreen.R;
import com.example.islamiccallscreen.base.BaseFragment;
import com.example.islamiccallscreen.databinding.FragmentQiblaBinding;

public class QiblaFragment extends BaseFragment<FragmentQiblaBinding>{
    private Context context;
    private Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = requireActivity();

    }
    @Override
    public FragmentQiblaBinding setBinding(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return FragmentQiblaBinding.inflate(inflater, container, false);
    }

    @Override
    public void initView() {

    }

    @Override
    public void bindView() {

    }
}