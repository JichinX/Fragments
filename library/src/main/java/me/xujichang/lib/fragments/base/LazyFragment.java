package me.xujichang.lib.fragments.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * me.xujichang.lib.fragments in Fragments
 * description:
 * 包含ViewModel+ViewBinding的Fragment
 * <p>
 *
 * @author xujichang at 2020/5/7 4:07 PM
 */
public abstract class LazyFragment extends Fragment {
    private AtomicBoolean isLoaded = new AtomicBoolean(false);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (checkInit()) {
            lazyInit();
            isLoaded.set(true);
        }
    }

    protected abstract void lazyInit();

    private boolean checkInit() {
        return isLoaded.get() && isHidden() && isVisible();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded.set(false);
    }
}
