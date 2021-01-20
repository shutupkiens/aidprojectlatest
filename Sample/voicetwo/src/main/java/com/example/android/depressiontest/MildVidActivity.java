package com.example.android.depressiontest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MildVidActivity extends AppCompatActivity {
    ListView listView;
    String[] vdourlkey = new String[]{
            "vParKngwgI8","iNsSl2jjuf4","sFtP0HWvu0k"
    };
    int length = vdourlkey.length;
    String thumbnailurl1 = "https://img.youtube.com/vi/";
    String thumbnailurl2 = "/hqdefault.jpg";
    String[] ListviewTitle = new String[]{
            "BREATHING EXERCISE","YOGA EXERCISE","SIMPLE WORKOUT"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mildvid);
        listView=findViewById(R.id.playlist);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MildVidActivity.this, VideoPlayerActivity.class);
                intent.putExtra("url", vdourlkey[position]);
                startActivity(intent);
            }
        });
    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.customlist, null);
            ImageView imageView = view.findViewById(R.id.imagelist);
            TextView textView = view.findViewById(R.id.titlelist);
            Picasso.get().load(thumbnailurl1+vdourlkey[position]+thumbnailurl2).into(imageView);
            textView.setText(ListviewTitle[position]);
            return view;
        }
    }
}