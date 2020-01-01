package com.example.weather_retro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PostDetailActivity  extends Activity {

    ImageView postImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        postImage = findViewById(R.id.post_image);

        // 상단 배너 Activity
        Intent in = getIntent();
        if (in.getIntExtra("idx",-1) != -1) {
            switch (in.getIntExtra("idx", -1)) {
                case 0:
                    postImage.setImageResource(R.drawable.banner1_detail);
                    break;
                case 1:
                    postImage.setImageResource(R.drawable.banner2_detail);
                    break;
                case 2:
                    postImage.setImageResource(R.drawable.banner3_detail);
                    break;
            }
        }
    }

    public void onClick(View v) {
        finish();
    }
}
