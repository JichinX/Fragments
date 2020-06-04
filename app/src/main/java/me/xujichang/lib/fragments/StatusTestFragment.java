package me.xujichang.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.xujichang.lib.fragments.base.BaseStatusFragment;
import me.xujichang.lib.fragments.databinding.FragmentStatusTestBinding;
import me.xujichang.lib.fragments.databinding.LayoutErrorViewBinding;
import me.xujichang.lib.fragments.databinding.LayoutLoadingViewBinding;

/**
 * Des:测试BaseStatusFragment的具体使用
 *
 * @author xujichang
 * Created by xujichang on 2020/6/4.
 * Copyright (c) 2020 xujichang All rights reserved.
 */
public class StatusTestFragment extends BaseStatusFragment {
    private FragmentStatusTestBinding mTestBinding;
    private LayoutErrorViewBinding mErrorViewBinding;
    private LayoutLoadingViewBinding mLoadingViewBinding;

    @Override
    protected View onCreateErrorView(LayoutInflater pInflater, ViewGroup pContainerView, Bundle pSavedInstanceState) {
        mErrorViewBinding = LayoutErrorViewBinding.inflate(pInflater);
        return mErrorViewBinding.getRoot();
    }

    @Override
    protected View onCreateLoadingView(LayoutInflater pInflater, ViewGroup pContainerView, Bundle pSavedInstanceState) {
        mLoadingViewBinding = LayoutLoadingViewBinding.inflate(pInflater);
        return mLoadingViewBinding.getRoot();
    }

    @Override
    protected View onCreateChildView(LayoutInflater pInflater, ViewGroup pContainerView, Bundle pSavedInstanceState) {
        mTestBinding = FragmentStatusTestBinding.inflate(pInflater);
        return mTestBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTestBinding.btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showError();
            }
        });
        mTestBinding.btnLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
            }
        });
        mTestBinding.btnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContent();
            }
        });
    }

    @Override
    protected void lazyInit() {

    }
}
