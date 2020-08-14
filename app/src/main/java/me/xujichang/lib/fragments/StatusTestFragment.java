/*
 *    Copyright 2020 许继昌 ：xujichang@outlook.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package me.xujichang.lib.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.xujichang.lib.fragments.base.BaseStatusFragment;
import me.xujichang.lib.fragments.databinding.FragmentStatusTestBinding;
import me.xujichang.lib.fragments.databinding.LayoutErrorViewBinding;
import me.xujichang.lib.fragments.databinding.LayoutLoadingViewBinding;

public class StatusTestFragment extends BaseStatusFragment {
    private FragmentStatusTestBinding mTestBinding;
    private LayoutErrorViewBinding mErrorViewBinding;
    private LayoutLoadingViewBinding mLoadingViewBinding;

    @Override
    protected View onCreateLoadingView(LayoutInflater pInflater, FrameLayout pFlLoading, Bundle pSavedInstanceState) {
        mLoadingViewBinding = LayoutLoadingViewBinding.inflate(pInflater);
        return mLoadingViewBinding.getRoot();
    }

    @Override
    protected View onCreateErrorView(LayoutInflater pInflater, FrameLayout pFlError, Bundle pSavedInstanceState) {
        mErrorViewBinding = LayoutErrorViewBinding.inflate(pInflater);
        return mErrorViewBinding.getRoot();
    }

    @Override
    protected View onCreateChildView(LayoutInflater pInflater, ViewGroup pContainerView, Bundle pSavedInstanceState) {
        mTestBinding = FragmentStatusTestBinding.inflate(pInflater);
        return mTestBinding.getRoot();
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTestBinding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    private void start() {
        showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showContent();
            }
        }, 1000);
    }

    @Override
    public void onDataInit() {

    }
}
