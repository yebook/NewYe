package com.kermitye.yesdk.base.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kermitye.yesdk.base.BasePresenter;
import com.kermitye.yesdk.base.IBaseActivity;
import com.kermitye.yesdk.base.IBaseModel;
import com.kermitye.yesdk.base.IBaseView;

/**
 * Created by kermitye on 2018/3/20.
 */

public abstract class BaseMVPCompatActivity<P extends BasePresenter, M extends IBaseModel> extends
        BaseCompatActivity implements IBaseActivity {
    protected P mPresent;
    private M mIModel;

    @Override
    protected void initData() {
        super.initData();
        mPresent = (P) initPresenter();
        if (mPresent != null) {
            mIModel = (M) mPresent.getModel();
            if (mIModel != null) {
                mPresent.attachMV(this, mIModel);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresent != null) {
            mPresent.detachMV();
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
    public void showToast(String msg) {
        //TODO
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        startActivity(clz);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        startActivityForResult(clz, bundle, requestCode);
    }

    @Override
    public void back() {
        super.onBackPressedSupport();
    }
}
