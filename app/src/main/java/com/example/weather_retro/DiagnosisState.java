package com.example.weather_retro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DiagnosisState extends AppCompatActivity {

    TextView textView_symptom;
    TextView lastline;
    Button button_go_to_home;
    Button button_re_diagnosis;

    ImageView imageView_symptom;

    LinearLayout no_symtom;
    LinearLayout has_symtom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_diagnosis);

        Intent intent = getIntent();

        int[] list = intent.getIntArrayExtra("list");

        button_go_to_home = (Button) findViewById(R.id.btn_gotohome);

        textView_symptom = (TextView) findViewById(R.id.symptom);
        lastline = (TextView) findViewById(R.id.last_text);
        imageView_symptom = (ImageView) findViewById(R.id.symptomImage);
        imageView_symptom.setImageResource(R.drawable.symptom_stethoscope);

        no_symtom = findViewById(R.id.no_symtom);
        has_symtom = findViewById(R.id.has_symtom);

        String disease = "";
        String Lastline = "으로 예상됩니다.";

        for (int i = 0; i < list.length; i++) {

            if (list[1] == 1 && (list[0] == 1 || list[2] == 1 || list[3] == 1)) {
                disease = getString(R.string.disease1);    // 식중독
                imageView_symptom.setImageResource(R.drawable.symptom_fa_23);
            } else if ((list[0] == 1 || list[3] == 1 || list[2] == 1) && list[14] == 1) {
                disease = getString(R.string.disease2);    // 일사병
                imageView_symptom.setImageResource(R.drawable.symptom_fa_22);
            } else if (list[0] == 1 && list[2] == 1 && list[6] == 1) {
                disease = getString(R.string.disease3);   // 뇌수막염
                imageView_symptom.setImageResource(R.drawable.symptom_fa_2);
            } else if (list[2] == 1 && list[7] == 1 && (list[3] == 1 || list[9] == 1)) {
                disease = getString(R.string.disease4);    // 열사병
                imageView_symptom.setImageResource(R.drawable.symptom_fa_12);
            } else if (list[11] == 1 && list[8] == 1 && (list[6] == 1 || list[4] == 1)) {
                disease = getString(R.string.disease5);;   // 저체온증
                imageView_symptom.setImageResource(R.drawable.symptom_fa_13);
            } else if (list[10] == 1 && (list[0] == 1 || list[1] == 1 || list[9] == 1 && list[11] == 1 || list[12] == 1)) {
                disease = getString(R.string.disease6);;     // 뇌졸중
                imageView_symptom.setImageResource(R.drawable.symptom_fa_3);
            } else if ((list[13] == 1 || list[8] == 1) && list[5] == 1) {
                disease = getString(R.string.disease7);;     //동상
                imageView_symptom.setImageResource(R.drawable.symptom_fa_20);
            } else if (list[10] == 1 && list[15] == 1 && list[12] == 1) {
                disease = getString(R.string.disease8);;     //결핵
                imageView_symptom.setImageResource(R.drawable.symptom_fa_21);
            } else if (list[2] == 1 && list[6] == 1 && list[17] == 1 && list[0] == 1) {
                disease = getString(R.string.disease9);;   //장티푸스
                imageView_symptom.setImageResource(R.drawable.symptom_fa_24);
            } else if ((list[1] == 1 || list[2] == 1 || list[13] == 1) && list[17] == 1 && list[10] == 1) {
                disease = getString(R.string.disease10);;    //알레르기 증상
                imageView_symptom.setImageResource(R.drawable.symptom_fa_25);
            }else {
                has_symtom.setVisibility(View.GONE);
                no_symtom.setVisibility(View.VISIBLE);

                disease = "";
                Lastline = "";
            }
        }
        textView_symptom.setText(disease);
        lastline.setText(Lastline);

        button_go_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
