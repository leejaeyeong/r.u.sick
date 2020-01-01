package com.example.weather_retro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class FragmentDiagnosis extends Fragment {

    ListView listView;
    DiagnosisAdapter adapter;
    Button button_complete;
    Button button_cancel;

    public FragmentDiagnosis() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_diagnosis, container,false);
        button_complete = v.findViewById(R.id.button_complete);
        //button_cancel = v.findViewById(R.id.button_diagnosis_cancel);


        listView = (ListView) v.findViewById(R.id.list_view);
        adapter = new DiagnosisAdapter();

        adapter.addItem(new DiagnosisContent(getString(R.string.symptom1), false));    // 두통 ,
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom2), false));  // 구토
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom3), false));   // 열감
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom4), false));   // 무력감
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom5), false));   // 근육떨림
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom6), false)); // 피부 질환
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom7), false));  // 오한
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom8), false)); // 피부 건조
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom9), false));  // 창백함
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom10), false));  // 어지럼증
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom11), false)); // 호흡곤란
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom12), false));    // 잠이 많아짐
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom13), false));    // 식욕없음
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom14), false));  // 피부 통증
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom15), false));  // 땀 분비
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom16), false));    //가래 혈담
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom17), false));  // 복통
        adapter.addItem(new DiagnosisContent(getString(R.string.symptom18), false)); // 붉은 반점

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        listView.setAdapter(adapter);

        button_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DiagnosisState.class);

                intent.putExtra("list", adapter.getList());
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                //finish();
            }
        });

        /*
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });*/


        return v;
    }

    class DiagnosisAdapter extends BaseAdapter {
        ArrayList<DiagnosisContent> items = new ArrayList<DiagnosisContent>();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Boolean> checklist = new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(DiagnosisContent item) {
            items.add(item);
            list.add(new Integer(0));
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public int[] getList() {
            int[] temp = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                temp[i] = list.get(i).intValue();
            }
            return temp;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {
            DiagnosisContentView view = new DiagnosisContentView(getContext());

            DiagnosisContent item = items.get(position);
            view.setContent((position + 1) + ". " + item.getContent());

            view.Cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    list.set(position, b ? new Integer(1) : new Integer(0));
                }
            });
            if(list.get(position)==1){
                view.Cbox.setChecked(true);
            }else {
                view.setCheckbox(item.getCheckbox());
            }

            return view;
        }
    }

}