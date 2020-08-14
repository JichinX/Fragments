package me.xujichang.lib.fragments.base;

public interface ILazyFragment {
    void onDataInit();

    boolean checkLazyInit();

    void tryInit();
}
