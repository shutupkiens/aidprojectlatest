package com.example.android.depressiontest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Activity11 extends AppCompatActivity {

    RecyclerView r1;
    String s1[], s2[], s3[];
    int imageResource[] = {R.drawable.greenteapot, R.drawable.chocolate, R.drawable.yogurt, R.drawable.ginger, R.drawable.egg, R.drawable.tea, R.drawable.sesame, R.drawable.banana};
    MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_11);
        r1 = (RecyclerView)findViewById(R.id.myRecycler1);
        s1 =getResources().getStringArray(R.array.haechan_cooking);
        s2 =getResources().getStringArray(R.array.price1);
        s3 =getResources().getStringArray(R.array.description1);
        adapter = new MenuAdapter(this, s1, s2, s3, imageResource);

        r1.setAdapter(adapter);
        r1.setLayoutManager(new LinearLayoutManager(this));

    }
}