package me.xujichang.lib.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.collect.Maps;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

/**
 * me.xujichang.lib.activities.util in Activities
 * description:
 * <p>
 * created by xujichang at 2020/4/28 7:11 PM
 * 对View的处理
 *
 * @author xujichang
 */
public class RxViews implements LifecycleEventObserver {
    /**
     * 根据LifecycleOwner 作为标识 存储RxViews
     */
    private static final ConcurrentMap<LifecycleOwner, RxViews> mViews = Maps.newConcurrentMap();

    private CompositeDisposable mDisposables;

    private RxViews(LifecycleOwner pOwner) {
        pOwner.getLifecycle().addObserver(this);
    }

    public static RxViews getInstance(LifecycleOwner pOwner) {
        RxViews vRxViews = mViews.get(pOwner);
        if (null == vRxViews) {
            vRxViews = new RxViews(pOwner);
            mViews.put(pOwner, vRxViews);
        }
        return vRxViews;
    }

    public void click(View view, final View.OnClickListener onClickListener) {
        addDisposable(RxView.clicks(view)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new ViewClickConsumer(view, onClickListener)));
    }

    private void addDisposable(Disposable pSubscribe) {
        getDisposables().add(pSubscribe);
    }

    private void dispose() {
        getDisposables().dispose();
    }

    public CompositeDisposable getDisposables() {
        if (null == mDisposables) {
            mDisposables = new CompositeDisposable();
        }
        return mDisposables;
    }

    public static <T> ObservableTransformer<T, T> ioToMainObservableSchedule() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        switch (event) {
            case ON_ANY:
            case ON_STOP:
            case ON_PAUSE:
            case ON_START:
            case ON_CREATE:
            case ON_RESUME:
            default:
                break;
            case ON_DESTROY:
                dispose();
                break;
        }
    }

    private static class ViewClickConsumer implements Consumer<Unit> {
        private View mView;
        private View.OnClickListener mListener;

        public ViewClickConsumer(View pView, View.OnClickListener pListener) {
            mView = pView;
            mListener = pListener;
        }

        @Override
        public void accept(Unit pUnit) throws Exception {
            mListener.onClick(mView);
        }
    }
}
