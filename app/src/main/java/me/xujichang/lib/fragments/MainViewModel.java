package me.xujichang.lib.fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import me.xujichang.lib.common.base.ListStatus;


/**
 * me.xujichang.lib.fragments in Fragments
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/11 6:06 PM
 */
public class MainViewModel extends AndroidViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<PagedList<MainFragment.TestObject>> getObjects() {
        return new LiveData<PagedList<MainFragment.TestObject>>() {
        };
    }

    public LiveData<ListStatus> getStatus() {
        return null;
    }
}
