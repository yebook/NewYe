package com.kermitye.yesdk.base;

import com.kermitye.yesdk.base.manager.RxManager;

import io.reactivex.annotations.NonNull;

/**
 * Created by kermitye on 2018/3/20.
 */

public abstract class BasePresenter<V, M> {
    public V mIView;
    public M mIModel;
    protected RxManager mRxManager = new RxManager();


    public void attachMV(@NonNull V v, @NonNull M m) {
        this.mIView = v;
        this.mIModel = m;
        onStart();
    }

    /**
     * 解绑IModel和IView
     */
    public void detachMV() {
        mRxManager.unSubscribe();
        mIView = null;
        mIModel = null;
    }

    /**
     * 返回presenter想持有的Model引用
     *
     * @return presenter持有的Model引用
     */
    public abstract M getModel();

    /**
     * IView和IModel绑定完成立即执行
     * <p>
     * 实现类实现绑定完成后的逻辑,例如数据初始化等,界面初始化, 更新等
     */
    public abstract void onStart();

}
