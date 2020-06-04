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

import me.xujichang.lib.common.util.RxViews;
import me.xujichang.lib.permissions.LivePermissions;
import me.xujichang.lib.permissions.PermissionResult;

/**
 * me.xujichang.lib.fragments.base in Fragments
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/9 5:47 PM
 */
public abstract class BaseFragment extends BaseStatusFragment {
    private AppCompatActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        initArgs(getArguments());
        mActivity = (AppCompatActivity) context;
    }

    public AppCompatActivity getAttachActivity() {
        return mActivity;
    }

    @Override
    protected View onCreateChildView(LayoutInflater pInflater, ViewGroup pContainerView, Bundle pSavedInstanceState) {
//        View vView = getView();
//        if (null == vView) {
        View vView;
        int layoutRes = getContentRes();
        if (-1 != layoutRes) {
            vView = pInflater.inflate(layoutRes, pContainerView, false);
        } else {
            vView = getContentView();
        }
        initView(vView);
        return vView;
    }

    protected abstract View getContentView();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void initView(View pView);

    protected int getContentRes() {
        return -1;
    }

    protected void initArgs(Bundle pArguments) {

    }

    public void click(View pView, View.OnClickListener pListener) {
        RxViews.getInstance(this).click(pView, pListener);
    }

    public void withPermissions(String[] permissions, Observer<PermissionResult> pObserver) {
        new LivePermissions(this)
                .requestPermissions(permissions)
                .observe(this, pObserver);
    }
}
