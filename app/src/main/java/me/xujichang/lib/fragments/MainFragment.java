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

package me.xujichang.lib.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import me.xujichang.lib.fragments.base.BaseVMFragment;
import me.xujichang.lib.fragments.databinding.FragmentMainBinding;
import me.xujichang.lib.fragments.util.FragmentUtil;

/**
 * me.xujichang.lib.fragments in Fragments
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/11 6:04 PM
 */
public class MainFragment extends BaseVMFragment<MainViewModel, FragmentMainBinding> {
    private PagedList<TestObject> mObjects;
    private TestPagedAdapter mAdapter;
    private static final String TAG = "MainFragment";

    @Override
    protected void lazyInit() {
        Log.i(TAG, "lazyInit: ");
    }

    @Override
    protected void onViewModelInit(MainViewModel pModel) {
        Log.i(TAG, "onViewModelInit: " + pModel);
//        FragmentUtil.bindList(this, mAdapter, mViewModel.getObjects(), mViewModel.getStatus());
    }

    @Override
    protected void onBindingInit(FragmentMainBinding pBinding) {
    }

    class TestObject {

    }

    class TestPagedAdapter extends PagedListAdapter<TestObject, Holder> {

        protected TestPagedAdapter() {
            super(new DiffUtil.ItemCallback<TestObject>() {
                @Override
                public boolean areItemsTheSame(@NonNull TestObject oldItem, @NonNull TestObject newItem) {
                    return false;
                }

                @Override
                public boolean areContentsTheSame(@NonNull TestObject oldItem, @NonNull TestObject newItem) {
                    return false;
                }
            });
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {

        }
    }

    private class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
