package com.example.weather_retro;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Objects;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


public class FragmentEmergency extends Fragment {
    private ArrayAdapter<String> Adapter;
    private ListView ListView;

    // 응급처치 상황
    String[] values = {
            "출혈",
            "화상",
            "골절",
            "심정지",
            "질식",
            "뱀에 물렸을 경우",
            "벌에 쏘였을 경우",
            "과호흡증후군",
            "일사병과 열사병"
    };


    public FragmentEmergency() {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_emergency, container,false);
        Adapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), R.layout.listview_font, values);
        ListView = view.findViewById(R.id.list);
        ListView.setAdapter(Adapter);
        ListView.setOnItemClickListener(onClickListItem);
        return view;
    }

    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(), EmergencyContent.class);
            String name = (String)parent.getAdapter().getItem(position);
            intent.putExtra("Title", name);
            intent.putExtra("Index", position);
            startActivity(intent);
        }
    };


}