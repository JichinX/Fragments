package me.xujichang.lib.fragments.base;

import androidx.fragment.app.Fragment;

import java.util.concurrent.atomic.AtomicBoolean;

public class LazyFragment extends Fragment {
    private AtomicBoolean isLoaded = new AtomicBoolean(false);


    @Override
    public void onResume() {
        super.onResume();
        if (checkInit()) {
            lazyInit();
            isLoaded.set(true);
        }
    }

    private boolean checkInit() {
        return isLoaded.get()&&isHidden()&&isVisible();
    }

    private void lazyInit() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded.set(false);
    }
}
