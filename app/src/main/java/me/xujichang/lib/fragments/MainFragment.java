package me.xujichang.lib.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import me.xujichang.lib.fragments.base.BaseVMFragment;
import me.xujichang.lib.fragments.databinding.FragmentMainBinding;

/**
 * me.xujichang.lib.fragments in Fragments
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/11 6:04 PM
 */
public class MainFragment extends BaseVMFragment<MainViewModel, FragmentMainBinding> {

    private static final String TAG = "MainFragment";

    @Override
    protected void lazyInit() {

    }

    @Override
    protected void onViewModelInit(MainViewModel pModel) {
        Log.i(TAG, "onViewModelInit: " + pModel);
    }

    @Override
    protected void onBindingInit(FragmentMainBinding pBinding) {

    }
}
