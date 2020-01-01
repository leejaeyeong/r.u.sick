package com.example.weather_retro;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class EmergencyContent extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    TextView name;
    ImageView img;
    TextView content;


    public void onClick(View v){
        if(v.getId() == R.id.back){
            finish();

        }
    }
    //YouTube Data API v3 서비스를 사용하기 API 키 필용
    //새 키를 생성하려면   Google APIs Console 에서 발급
    //private static final String YoutubeDeveloperKey = "AIzaSyDEP5fH9dm3aN9ShwgnzwYi3YaZ4ODLFm8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_content);
        Log.d("youtube Test",
                "사용가능여부:"+ YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this)); //SUCCSESS
        //YouTubePlayer를 초기화
        //public void initialize(String yo, YouTubePlayer.OnInitializedListener onInitializedListener)

        //YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        //youTubeView.initialize(YoutubeKey.youtube_key, this);

        getYouTubePlayerProvider().initialize(YoutubeKey.youtube_key,this);
        name = (TextView) findViewById(R.id.name);
        content = (TextView) findViewById(R.id.content);
        img = (ImageView) findViewById(R.id.img);
        Intent intent = getIntent();
        String title = intent.getStringExtra("Title") + " 응급처치";
        int position = intent.getExtras().getInt("Index");

        switch (position){
            case 0:     //출혈
                name.setText(title);
                content.setText(getString(R.string.emergency_blood));
                img.setImageResource(R.drawable.emergency_blood);
                break;
            case 1:     //화상
                name.setText(title);
                content.setText(getString(R.string.emergency_scalded));
                img.setImageResource(R.drawable.emergency_fire);
                break;
            case 2:     //골절
                name.setText(title);
                content.setText(getString(R.string.emergency_fracture));
                img.setImageResource(R.drawable.emergency_broke);
                break;
            case 3:     //심정지
                name.setText(title);
                img.setImageResource(R.drawable.emergency_chest);
                break;
            case 4:     //질식
                name.setText(title);
                img.setImageResource(R.drawable.emergency_nobreath);
                break;
            case 5:     //뱀에 물렸을 때
                name.setText(title);
               content.setText(getString(R.string.emergency_snake));
                img.setImageResource(R.drawable.emergency_snake);
                break;
            case 6:     // 벌에 쏘였을 때
                name.setText(title);
                content.setText(getString(R.string.emergency_bee));
                img.setImageResource(R.drawable.emergency_bee);
                break;
            case 7: // 과호흡
                name.setText(title);
                content.setText(getString(R.string.emergency_hyperventilation));
                img.setImageResource(R.drawable.emergency_breath);
                break;
            case 8:     // 열사병
                name.setText(title);
                content.setText(getString(R.string.emergency_heatstroke));
                img.setImageResource(R.drawable.emergency_hot);
                break;

        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
        if (!b) {
            //player.cueVideo("M_cznu43DD4"); //video id.

            Intent intent = getIntent();
            int position = intent.getExtras().getInt("Index");
            switch(position){
                case 0:
                    player.cueVideo("M_cznu43DD4");  //https://www.youtube.com/watch?v=M_cznu43DD4
                    break;
                case 1:
                    player.cueVideo("cpPBRYu9waU");  //https://www.youtube.com/watch?v=cpPBRYu9waU
                    break;
                case 2:
                    player.cueVideo("-PGJa0d4344");  //https://www.youtube.com/watch?v=-PGJa0d4344
                    break;
                case 3:
                    player.cueVideo("Zbp74ri21YE&t=6s");  //https://www.youtube.com/watch?v=Zbp74ri21YE&t=6s
                    break;
                case 4:
                    player.cueVideo("Tt0QLa1zQM4");//https://www.youtube.com/watch?v=Tt0QLa1zQM4
                    break;
                case 5:
                    player.cueVideo("J-eAHgmPf-M");//https://www.youtube.com/watch?v=J-eAHgmPf-M
                    break;
                case 6:
                    player.cueVideo("FkWf3o8T_2U");//https://www.youtube.com/watch?v=FkWf3o8T_2U
                    break;
                case 7:
                    player.cueVideo("_NC2mzCotZI");//https://www.youtube.com/watch?v=_NC2mzCotZI
                    break;
                case 8:
                    player.cueVideo("_E-Zp0Tteqk");//https://www.youtube.com/watch?v=_E-Zp0Tteqk
                    break;
            }

        }

    }

    //플레이어가 초기화되지 못할 때 호출됩니다.
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(YoutubeKey.youtube_key, this);
        }
    }
}