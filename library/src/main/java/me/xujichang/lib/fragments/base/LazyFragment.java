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
        tryLoadData();
    }

    private void tryLoadData() {
        if (checkInit()) {
            lazyInit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tryLoadData();
    }

    protected abstract void lazyInit();

    private boolean checkInit() {
        return isLoaded.compareAndSet(false, true) && !isHidden();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded.set(false);
    }
}
