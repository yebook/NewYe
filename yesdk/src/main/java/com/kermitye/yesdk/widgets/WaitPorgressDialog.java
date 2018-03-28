package com.kermitye.yesdk.widgets;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by kermitye on 2018/3/16.
 */

public class WaitPorgressDialog extends ProgressDialog {
    public WaitPorgressDialog(Context context) {
        super(context, 0);
    }
    public WaitPorgressDialog(Context context, int theme) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
    }
}
