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

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.xujichang.lib.common.rx.RxViews;
import me.xujichang.lib.permissions.LivePermissions;
import me.xujichang.lib.permissions.PermissionResult;

/**
 * me.xujichang.lib.fragments.base in Fragments
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/9 5:47 PM
 */
public abstract class BaseFragment extends LazyFragment {
    private AppCompatActivity mActivity;
    //    private View mRootView;
//    private final List<ViewBinding> mBindings = new ArrayList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        initArgs(getArguments());
        mActivity = (AppCompatActivity) context;
    }

    public AppCompatActivity getAttachActivity() {
        return mActivity;
    }

    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (null == mRootView) {
//            int layoutRes = getContentRes();
//            if (-1 != layoutRes) {
//                mRootView = inflater.inflate(layoutRes, container, false);
//            } else {
//                mRootView = getContentView();
//            }
//        } else {
//            checkOrDetachParent(mRootView);
//        }
//        initView(mRootView);
//        return mRootView;
//    }
    @Deprecated
    protected abstract View getContentView();

    protected void initArgs(Bundle pArguments) {

    }

    protected void checkOrDetachParent(View pChild) {
        if (null != pChild) {
            if (null != pChild.getParent()) {
                ((ViewGroup) pChild.getParent()).removeView(pChild);
            }
        }
    }

    public void click(View pView, View.OnClickListener pListener) {
        RxViews.getInstance(getViewLifecycleOwner()).click(pView, pListener);
    }

    public void withPermissions(String[] permissions, Observer<PermissionResult> pObserver) {
        new LivePermissions(this)
                .requestPermissions(permissions)
                .observe(this, pObserver);
    }

//    protected void addBinding(ViewBinding pViewBinding) {
////        mBindings.add(pViewBinding);
//    }

    @Override
    public void onDestroyView() {
//        releaseBindings();
        super.onDestroyView();
    }

    private void releaseBindings() {
//        Iterator<ViewBinding> vIterator = mBindings.iterator();
//        if (vIterator.hasNext()) {
//            ViewBinding vBinding = vIterator.next();
//            vBinding = null;
//            vIterator.remove();
//        }
    }
}
