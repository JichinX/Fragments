package me.xujichang.lib.fragments.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * me.xujichang.lib.fragments.base in Fragments
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/9 5:47 PM
 */
public abstract class BaseFragment extends LazyFragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vView = getView();
        if (null == vView) {
            int layoutRes = getContentRes();
            if (-1 != layoutRes) {
                vView = inflater.inflate(layoutRes, container, false);
            } else {
                vView = getContentView();
            }
            initView(vView);
        } else {
            if (null != vView.getParent()) {
                ((ViewGroup) vView.getParent()).removeView(vView);
            }
        }
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
}
