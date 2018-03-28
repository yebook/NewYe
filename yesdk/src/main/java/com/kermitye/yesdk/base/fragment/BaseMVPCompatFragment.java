package com.kermitye.yesdk.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kermitye.yesdk.base.BasePresenter;
import com.kermitye.yesdk.base.IBaseFragment;
import com.kermitye.yesdk.base.IBaseModel;
import com.kermitye.yesdk.base.activity.BaseCompatActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by kermitye on 2018/3/27.
 */

public abstract class BaseMVPCompatFragment<P extends BasePresenter, M extends IBaseModel> extends
        BaseCompatFragment implements IBaseFragment {

    public P mPresenter;
    public M mIModel;


    @Override
    public void initData() {
        super.initData();
        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mIModel = (M) mPresenter.getModel();
            if (mIModel != null) {
                mPresenter.attachMV(this, mIModel);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
        }
    }

    @Override
    public void showWaitDialog(String waitMsg) {
        showProgressDialog(waitMsg);
    }

    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }

    @Override
    public void back() {
        this.onBackPressedSupport();
    }

    @Override
    public void startNewFragment(@NonNull SupportFragment supportFragment) {
        start(supportFragment);
    }

    @Override
    public void startNewFragmentWithPop(@NonNull SupportFragment supportFragment) {
        startWithPop(supportFragment);
    }

    @Override
    public void startNewFragmentForResult(@NonNull SupportFragment supportFragment, int
            requestCode) {
        startForResult(supportFragment, requestCode);
    }

    @Override
    public void popToFragment(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        popTo(targetFragmentClass, includeTargetFragment);
    }

    @Override
    public void hideKeybord() {
        hideSoftInput();
    }


    @Override
    public void setOnFragmentResult(int resultCode, Bundle data) {
        setFragmentResult(resultCode, data);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        ((BaseCompatActivity) mActivity).startActivity(clz);
    }
    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        ((BaseCompatActivity) mActivity).startActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        ((BaseCompatActivity) mActivity).startActivityForResult(clz, bundle, requestCode);
    }

    @Override
    public boolean isVisiable() {
        return isSupportVisible();
    }

    @Override
    public Activity getBindActivity() {
        return mActivity;
    }
}
