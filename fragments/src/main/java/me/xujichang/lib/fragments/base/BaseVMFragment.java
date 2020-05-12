package me.xujichang.lib.fragments.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.xujichang.lib.fragments.base.LazyFragment;
import me.xujichang.lib.utils.ClassUtils;

/**
 * me.xujichang.lib.fragments in Fragments
 * description:
 * 包含ViewModel+ViewBinding的Fragment
 * <p>
 *
 * @author xujichang at 2020/5/7 4:07 PM
 */
public abstract class BaseVMFragment<VM extends ViewModel, VB extends ViewBinding> extends BaseFragment {

    protected VM mViewModel;
    protected VB mViewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class<VB> vVBClass = ClassUtils.getVMClass(getClass(), 1);
        if (vVBClass != null) {
            try {
                Method vMethod = vVBClass.getMethod("inflate", LayoutInflater.class);
                mViewBinding = (VB) vMethod.invoke(null, getLayoutInflater());

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException pE) {
                pE.printStackTrace();
            }
        }
        Class<VM> vVMClass = ClassUtils.getVMClass(getClass(), 0);
        if (null != vVMClass) {
            mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(vVMClass);
            onViewModelInit(mViewModel);
        }
    }

    @Override
    protected void initView(View pView) {
        if (mViewBinding != null) {
            onBindingInit(mViewBinding);
        }
    }

    @Override
    protected View getContentView() {
        return mViewBinding.getRoot();
    }

    protected abstract void onViewModelInit(VM pModel);

    protected abstract void onBindingInit(VB pBinding);
}
