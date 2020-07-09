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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.xujichang.lib.common.util.ClassUtils;
import me.xujichang.lib.fragments.base.LazyFragment;

/**
 * me.xujichang.lib.fragments in Fragments
 * description:
 * 包含ViewModel+ViewBinding的Fragment
 * <p>
 *
 * @author xujichang at 2020/5/7 4:07 PM
 */
public abstract class BaseVMFragment<VM extends ViewModel, VB extends ViewBinding> extends BaseStatusFragment {

    protected VM mViewModel;
    protected VB mViewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class<VM> vVMClass = ClassUtils.getVMClass(getClass(), 0);
        if (null != vVMClass) {
            mViewModel = onCreateViewModelProvider().get(vVMClass);
            onViewModelInit(mViewModel);
        }
    }

    @Override
    protected View onCreateChildView(LayoutInflater pInflater, ViewGroup pFlContainer, Bundle pSavedInstanceState) {
        Class<VB> vVBClass = ClassUtils.getVMClass(getClass(), 1);
        if (vVBClass != null) {
            try {
                Method vMethod = vVBClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
                mViewBinding = (VB) vMethod.invoke(null, pInflater, pFlContainer, false);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException pE) {
                pE.printStackTrace();
            }
        }
        onBindingInit(mViewBinding);
        return mViewBinding.getRoot();
    }

    protected ViewModelProvider onCreateViewModelProvider() {
        if (null == getAttachActivity()) {
            return new ViewModelProvider(this);
        }
        return new ViewModelProvider(getAttachActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewBinding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected View getContentView() {
        return mViewBinding.getRoot();
    }

    protected abstract void onViewModelInit(VM pModel);

    protected abstract void onBindingInit(VB pBinding);
}
