package com.kermitye.yesdk.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kermitye.yesdk.base.BaseApplication;
import com.kermitye.yesdk.widgets.WaitPorgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by kermitye on 2018/3/22.
 */

public abstract class BaseCompatFragment extends SupportFragment {
    protected String TAG;
    protected Activity mActivity;
    protected Context mContext;
    protected BaseApplication mApplication;
    protected WaitPorgressDialog mWaitPorgressDialog;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TAG = getClass().getSimpleName();
        mUnbinder = ButterKnife.bind(this, view);
        getBundle(getArguments());
        initData();
        initUI(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    /**
     * 处理回退事件
     *
     * @return true 事件已消费
     * <p>
     * false 事件向上传递
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            //如果当前存在fragment>1，当前fragment出栈
            pop();
        } else {
            //已经退栈到root fragment，交由Activity处理回退事件
            return false;
        }
        return true;

    }

    /**
     * 在监听器之前把数据准备好
     */
    public void initData() {
        mWaitPorgressDialog = new WaitPorgressDialog(mActivity);
        mContext = BaseApplication.getContext();
        mApplication = (BaseApplication) mActivity.getApplication();
    }

    /**
     * 显示提示框
     *
     * @param msg 提示框内容字符串
     */
    protected void showProgressDialog(String msg) {
        if (mWaitPorgressDialog.isShowing()) {
            mWaitPorgressDialog.dismiss();
        }

        mWaitPorgressDialog.setMessage(msg);
        mWaitPorgressDialog.show();
    }

    /**
     * 隐藏提示框
     */
    protected void hideProgressDialog() {
        if (mWaitPorgressDialog != null) {
            mWaitPorgressDialog.dismiss();
        }
    }


    public void getBundle(Bundle bundle) {
    }



    public abstract void initUI(View view, @Nullable Bundle savedInstanceState);
    public abstract int getLayoutId();
}
