package com.example.weather_retro;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends Activity {         // 앱 실행 로딩
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },3000);
    }
}
