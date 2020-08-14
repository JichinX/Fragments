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
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * me.xujichang.lib.fragments in Fragments
 * description:
 * 懒加载实现
 * <p>
 *
 * @author xujichang at 2020/5/7 4:07 PM
 */
public abstract class LazyFragment extends Fragment implements ILazyFragment {
    private static final int FLAG_CONTAINER_VIEWPAGER_2 = 2;
    private static final int FLAG_CONTAINER_VIEWPAGER = 1;
    private static final int FLAG_CONTAINER_DEFAULT = 0;
    private int mContainerFlag = FLAG_CONTAINER_DEFAULT;
    /**
     * 数据是否已加载
     */
    private final AtomicBoolean isLoaded = new AtomicBoolean(false);
    /**
     * View 状态
     */
    private final AtomicBoolean isPrepared = new AtomicBoolean(false);

    /**
     * View 在先
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkContainer(view.getParent());
        isPrepared.set(true);
        tryInit();
    }

    private void checkContainer(ViewParent pParent) {
        if (pParent instanceof ViewPager) {
            mContainerFlag = FLAG_CONTAINER_VIEWPAGER;
        } else if (pParent instanceof ViewPager2) {
            mContainerFlag = FLAG_CONTAINER_VIEWPAGER_2;
        } else {
            mContainerFlag = FLAG_CONTAINER_DEFAULT;
        }
    }

    /**
     * activity 在后
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            tryInit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tryInit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded.set(false);
        isPrepared.set(false);
    }

    @Override
    public boolean checkLazyInit() {
        if (mContainerFlag == FLAG_CONTAINER_VIEWPAGER) {
            return getUserVisibleHint() && isPrepared.get() && !isLoaded.get();
        } else {
            return isResumed() && isPrepared.get() && !isLoaded.get();
        }
    }

    @Override
    public void tryInit() {
        if (checkLazyInit()) {
            onDataInit();
            isLoaded.set(true);
        }
    }

    @Override
    public void onDataInit() {
        lazyInit();
    }

    @Deprecated
    protected abstract void lazyInit();
}
