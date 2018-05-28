package com.kermitye.newye;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kermitye.yesdk.error.ApiException;
import com.kermitye.yesdk.helper.RetrofitCreateHelper;
import com.kermitye.yesdk.helper.RxHelper;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitCreateHelper.createApi(Api.class, Api.HOST).getUsers().compose(RxHelper.rxSchedulerHelper()).subscribe(listResult -> {
            Toast.makeText(this, "user list: " + listResult.data.size() + " / " + listResult.data, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onCreate: " + "user list: " + listResult.data.size() + " / " + listResult.data);
        }, throwable -> {
            Log.e(TAG, "onCreate: error" + throwable.getMessage());
        });


    }

    public void start(View view) {
        /*RetrofitCreateHelper.createApi(Api.class, Api.HOST).getUser().compose(RxHelper.rxSchedulerHelper()).subscribe(userResult -> {
            Toast.makeText(this, "user:" + userResult.data.toString(), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "start: " + "user:" + userResult.data.toString());
        }, throwable -> {
            Log.e(TAG, "start: error" + throwable.getMessage());
        });*/
        getUser();
    }


    public void getUser() {
        RetrofitCreateHelper.createApi(Api.class, Api.HOST)
                .getUser()
                .compose(RxHelper.rxSchedulerHelper())
                .compose(RxHelper.trans())
                .subscribe(user -> {
                    Toast.makeText(this, "user:" + user.toString(), Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    if (throwable instanceof ApiException) {
                        ApiException apiException = (ApiException) throwable;
                        Log.e(TAG, "====onError: " + apiException.getErrorCode() + " / " + apiException.getMessage());
                    } else {
                        Log.e(TAG, "onError: " + throwable.getMessage());
                    }
                });
    }

}
