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

package me.xujichang.lib.fragments.base;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import me.xujichang.lib.fragments.databinding.FragmentBaseStatusContainerBinding;
import me.xujichang.lib.fragments.databinding.LayoutErrorViewBinding;
import me.xujichang.lib.fragments.databinding.LayoutLoadingViewBinding;

import static androidx.constraintlayout.widget.ConstraintSet.GONE;
import static androidx.constraintlayout.widget.ConstraintSet.INVISIBLE;
import static androidx.constraintlayout.widget.ConstraintSet.VISIBLE;

/**
 * @author xujichang on 2020/5/13.
 */
public abstract class BaseStatusFragment extends LazyFragment {
    private FragmentBaseStatusContainerBinding mContainerBinding;
    private ConstraintSet mConstraintSet = new ConstraintSet();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vView = getView();
        if (null == vView) {
            mContainerBinding = FragmentBaseStatusContainerBinding.inflate(inflater);
            View child = onCreateChildView(inflater, mContainerBinding.flContainer, savedInstanceState);
            checkParent(child);
            mContainerBinding.flContainer.addView(child);
            View loadView = onCreateLoadingView(inflater, mContainerBinding.flLoading, savedInstanceState);
            if (null != loadView) {
                checkParent(loadView);
                mContainerBinding.flLoading.addView(loadView);
            }
            View errorView = onCreateErrorView(inflater, mContainerBinding.flError, savedInstanceState);
            if (null != errorView) {
                checkParent(errorView);
                mContainerBinding.flError.addView(errorView);
            }
            mConstraintSet.clone(mContainerBinding.getRoot());
            vView = mContainerBinding.getRoot();
        } else {
            if (null != vView.getParent()) {
                ((ViewGroup) vView.getParent()).removeView(vView);
            }
        }
        return vView;
    }

    protected abstract View onCreateLoadingView(LayoutInflater pInflater, FrameLayout pFlLoading, Bundle pSavedInstanceState);

    protected abstract View onCreateErrorView(LayoutInflater pInflater, FrameLayout pFlError, Bundle pSavedInstanceState);

    private void checkParent(View pChild) {
        if (null != pChild) {
            if (null != pChild.getParent()) {
                ((ViewGroup) pChild.getParent()).removeView(pChild);
            }
        }
    }

    protected abstract View onCreateChildView(LayoutInflater pInflater, ViewGroup pFlContainer, Bundle pSavedInstanceState);


    protected void showError() {
        showView(mContainerBinding.flError, mContainerBinding.getRoot());
    }

    protected void showLoading() {
        showView(mContainerBinding.flLoading, mContainerBinding.getRoot());
    }

    protected void showContent() {
        showView(mContainerBinding.flContainer, mContainerBinding.getRoot());
    }

    private void showView(View pView, ConstraintLayout pRoot) {
        int count = pRoot.getChildCount();
        int showViewId = pView.getId();
        for (int index = 0; index < count; index++) {
            View child = pRoot.getChildAt(index);
            int childId = child.getId();
            mConstraintSet.setVisibility(childId, childId == showViewId ? VISIBLE : GONE);
        }
        TransitionManager.beginDelayedTransition(pRoot);
        mConstraintSet.applyTo(pRoot);
    }

}
