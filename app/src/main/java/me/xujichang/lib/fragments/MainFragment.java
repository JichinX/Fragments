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
