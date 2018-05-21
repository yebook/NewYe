package com.kermitye.newye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = "{\"code\":0,\"message\":\"success\",\"data\":[{\"name\":\"ye\"," +
                "\"age\":16},{\"name\":\"ykm\",\"age\":18},{\"name\":\"ke\",\"age\":20}]}";
    }


}
