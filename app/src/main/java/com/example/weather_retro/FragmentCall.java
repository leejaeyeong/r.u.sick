package com.example.weather_retro;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class FragmentCall extends Fragment {
    GridView gridView;

    public FragmentCall() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_call, null);
        gridView = (GridView)view.findViewById(R.id.gridview_call);
        gridView.setAdapter(new ImageAdapter(view.getContext()));

        gridView.setOnItemClickListener(onClickListItem);


        return view;
    }

    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {
        //한번 클릭 했을 때
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = null;

            switch (position) {
                case 0 : // 응급구조신고
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:119"));
                    break;
                case 1 : // 해양긴급신고
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:122"));
                    break;
                case 2 : // 외래병사고신고
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0549120616"));
                    break;
                case 3 : // 감염병사고신고
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1339"));
                    break;
                case 4 : // ?
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:129"));
                    break;
                case 5 :// ?
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:043-719-7700"));
                    break;
                default:
                    break;

            }
            startActivity(intent);

        }
    };

    public class ImageAdapter extends BaseAdapter {
        private Context context;

        private Integer[] images = { R.drawable.call_emergency, R.drawable.call_sea,
                                    R.drawable.call_foreign_disease, R.drawable.call_gamyeom,
                R.drawable.call_bogun,R.drawable.call_administrate_disease,};

        public ImageAdapter(Context con) {
            this.context = con;
        }

        public int getCount() {
            return images.length;
        }

        public Object getItem(int pos) {
            return null;
        }
        public long getItemId(int pos) {
            return 0;
        }
        public View getView(int pos, View convertView, ViewGroup parent) {
            ImageView imageView;

            if(convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(500,400));
                imageView.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(0,0,0,20);
            }
            else {
                imageView=(ImageView)convertView;
            }
            imageView.setImageResource(images[pos]);
            return imageView;
        }
    }
}
