package com.example.weather_retro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

public class FragmentMain extends Fragment {
    TextView temper, humidity, wind, city, coment;
    ImageView weather_view;
    ProgressBar progressBarHumidity;
    ProgressBar progressBarWindSpeed;
    ViewFlipper flipper;
    ImageButton hospital, ambulance, heartbeat;
    String sx;
    int value_of_humidity;
    double value_of_temper, value_of_max_temper, value_of_min_temper, value_of_wind_speed;
    String value_of_weather, value_of_city;
    int images[] = {R.drawable.banner1,R.drawable.banner2,R.drawable.banner3};


    public FragmentMain() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, null);

        hospital = (ImageButton) view.findViewById(R.id.hospital_building);
        ambulance = (ImageButton) view.findViewById(R.id.amburance);
        heartbeat = (ImageButton) view.findViewById(R.id.heart);

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent. ACTION_VIEW , Uri. parse ("https://www.e-gen.or.kr/egen/search_hospital.do"));
                startActivity(intent);

            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent. ACTION_VIEW , Uri. parse ("https://www.e-gen.or.kr/egen/search_commercial_ambulance.do"));
                startActivity(intent);

            }
        });
        heartbeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent. ACTION_VIEW , Uri. parse ("https://www.e-gen.or.kr/egen/search_aed.do"));
                startActivity(intent);

            }
        });

        temper = (TextView)view.findViewById(R.id.tv_current_temper);
        wind = view.findViewById(R.id.tv_wind_speed);
        humidity = (TextView)view.findViewById(R.id.tv_humidity);
        city = (TextView)view.findViewById(R.id.tv_city);


        progressBarHumidity = view.findViewById(R.id.pb_humidity);
        progressBarWindSpeed = view.findViewById(R.id.pb_wind_spped);
        progressBarWindSpeed.setMax(10); // 태풍급 풍속이 20-30

        value_of_humidity = Integer.parseInt(getArguments().getString("humid"));
        value_of_temper = Double.valueOf(getArguments().getString("temper"));
        value_of_wind_speed = Double.valueOf(getArguments().getString("wind"));
        value_of_min_temper = Double.valueOf(getArguments().getString("min"));
        value_of_max_temper = Double.valueOf(getArguments().getString("max"));
        value_of_weather = getArguments().getString("weather");
        value_of_city = getArguments().getString("city");

        if(value_of_city.equals("Tenan"))
            value_of_city = "천안시";

        humidity.setText(getArguments().getString("humid") + "%");
        temper.setText(getArguments().getString("temper") + "°C");
        wind.setText(getArguments().getString("wind") + "m/s");
        city.setText(value_of_city);

        progressBarHumidity.setProgress(Integer.parseInt(getArguments().getString("humid")));
        progressBarWindSpeed.setProgress(1);
        flipper = view.findViewById(R.id.image_slider);
        for(int image : images)
            flipperImages(image);

        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (flipper.getDisplayedChild()) {
                    default:
                        //Toast.makeText(getActivity(), Integer.toString(flipper.getDisplayedChild())+"번째 배너 클릭",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getActivity(), PostDetailActivity.class);
                        in.putExtra("idx",flipper.getDisplayedChild());
                        startActivity(in);
                }
        }
        });



        weather_view = view.findViewById(R.id.imageView);
        switch(value_of_weather)
        {
            case "Clear":
                weather_view.setImageResource(R.drawable.weather_sun);
                break;

            case "Snow":
                weather_view.setImageResource(R.drawable.weather_snowflake);
                break;

            case "Rain":
            case "Drizzle":
                weather_view.setImageResource(R.drawable.weather_rain);
                break;

            case "Thunderstorm":
                weather_view.setImageResource(R.drawable.weather_storm);
                break;

            case "Clouds":
                weather_view.setImageResource(R.drawable.weather_cloud);
                break;

            case "Mist":
            case "Smoke":
            case "Haze":
            case "Dust":
            case "Fog":
            case "Sand":
            case "Ash":
            case "Squall":
            case "Tornado":
                weather_view.setImageResource(R.drawable.weather_foggy);
                break;


            default:
                weather_view.setImageResource(R.drawable.weather_moon);
                break;
        }

        //weather_view.setImageResource(R.drawable.weather_sun);

        coment = (TextView)view.findViewById(R.id.tv_coment);

        if((value_of_max_temper - value_of_min_temper) > 10.0) {
            if (value_of_humidity < 30.0)
                coment.setText("일교차가 크고 건조합니다!!! 감기에 각별히 유의하세요 :(");
            else
                coment.setText("일교차가 큽니다! 일교차가 큰 날엔 감기 발병률이 높아지니 감기 조심하세요 :(");
        }
        else if((value_of_humidity > 80) && (value_of_temper > 25))
            coment.setText("음식이 상하기 좋은 날씨 입니다!!! 식중독에 각별히 유의하세요 :(");
        else if(value_of_wind_speed > 14.0)
            coment.setText("강풍이 불고 있습니다!! 야외 활동할 때 바람에 날리는 물건에 맞지 않게 조심하세요");


        coment.setSingleLine(true);
        coment.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        coment.setSelected(true);
        return view;
    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        flipper.addView(imageView);      // 이미지 추가
        flipper.setFlipInterval(2000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        flipper.setAutoStart(true);          // 자동 시작 유무 설정

        // animation
        flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        flipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
    }

}
