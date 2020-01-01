package com.example.weather_retro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {


    TextView title;
    ImageView buttonFragmentMain;
    ImageView buttonFragmentCall;
    ImageView buttonFragmentDiagnosis;
    ImageView buttonFragmentEmergency;
    String str_temper = "";
    String str_humidity = "";
    String str_max_temper = "";
    String str_min_temper = "";
    String str_weather = "";
    String str_wind = "";
    String str_city = "";
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent in = new Intent(this,LoadingActivity.class);
        startActivity(in);

        title = findViewById(R.id.maintitle);
        Retrofit client = new Retrofit.Builder().baseUrl("http://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();

        ApiInterface service = client.create(ApiInterface.class);
        Call<Repo> call = service.repo("bdeb951d8b7b3aa8335518eb8e538783", 36.806499, 127.152199); // default 천안시

        weatherCall(call);

        buttonFragmentMain = findViewById(R.id.btn_fragment_main);
        buttonFragmentCall = findViewById(R.id.btn_fragment_call);
        buttonFragmentDiagnosis = findViewById(R.id.btn_fragment_diagonosis);
        buttonFragmentEmergency = findViewById(R.id.btn_fragment_emergency);

        //메인 화면 프래그먼트 전환
        buttonFragmentMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                changeMenuImage();
                callFragment(0);
                buttonFragmentMain.setImageResource(R.drawable.icon_home_color);
            }
        });

        // 응급전화 화면 프래그먼트 전환
        buttonFragmentCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMenuImage();
                callFragment(1);
                buttonFragmentCall.setImageResource(R.drawable.icon_call_color);
            }
        });

        // 진단하기 화면 프래그먼트 전환
        buttonFragmentDiagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMenuImage();
                callFragment(2);
                buttonFragmentDiagnosis.setImageResource(R.drawable.icon_linegraph_color);
            }
        });

        // 응급처치 화면 프래그먼트 전환
        buttonFragmentEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMenuImage();
                callFragment(3);
                buttonFragmentEmergency.setImageResource(R.drawable.icon_siren_color);
            }
        });

        buttonFragmentMain.setImageResource(R.drawable.icon_home_color);

    }


    private void weatherCall(final Call<Repo> call){
        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Response<Repo> response) {
                if (response.isSuccess()) {

                    Repo repo = response.body();
                    str_temper = String.valueOf(repo.getMain().getTemp());
                    str_humidity = String.valueOf(repo.getMain().getHumidity());
                    str_max_temper = String.valueOf(repo.getMain().getTemp_max());
                    str_min_temper = String.valueOf(repo.getMain().getTemp_min());
                    str_weather = String.valueOf(repo.getWeather().get(0).getmain());
                    str_wind = String.valueOf(repo.getWind().getSpeed());
                    str_city = String.valueOf(repo.getName());

                    bundle.putString("weather", str_weather);
                    bundle.putString("humid", str_humidity);
                    bundle.putString("temper", str_temper);
                    bundle.putString("max", str_max_temper);
                    bundle.putString("min", str_min_temper);
                    bundle.putString("wind", str_wind);
                    bundle.putString("city", str_city);
                    callFragment(0);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    private void changeMenuImage() {
        buttonFragmentMain.setImageResource(R.drawable.icon_home);
        buttonFragmentCall.setImageResource(R.drawable.icon_call);
        buttonFragmentDiagnosis.setImageResource(R.drawable.icon_linegraph);
        buttonFragmentEmergency.setImageResource(R.drawable.icon_siren);
    }

    private void callFragment(int id) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        switch (id){
            case 0:
                // '메인화면 프래그먼트' 호출
                title.setText("어디아파?");
                FragmentMain fragment0 = new FragmentMain();
                fragment0.setArguments(bundle);
                tr.replace(R.id.fragment_container, fragment0);
                tr.commit();
                // tr.commit();
                break;
            case 1:
                // '응급 전화 프래그먼트' 호출
                title.setText("긴급전화");
                FragmentCall fragmentCall = new FragmentCall();
                tr.replace(R.id.fragment_container, fragmentCall);
                tr.commit();
                break;
            case 2:
                // '진단 하기 프래그먼트' 호출
                title.setText("진단하기");
                FragmentDiagnosis fragmentDiagnosis = new FragmentDiagnosis();
                tr.replace(R.id.fragment_container, fragmentDiagnosis);
                tr.commit();
                break;
            case 3:
                // '응급 처치 프래그먼트' 호출
                title.setText("응급처치");
                FragmentEmergency fragmentEmergency = new FragmentEmergency();
                tr.replace(R.id.fragment_container, fragmentEmergency);
                tr.commit();
                break;
        }
    }
}
