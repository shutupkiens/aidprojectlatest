package com.example.android.depressiontest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Activity1 extends AppCompatActivity {

    RecyclerView r1;
    String s1[], s2[], s3[];
    int imageResource[] = {R.drawable.animation_tomato, R.drawable.animation_onion, R.drawable.animation_leaf, R.drawable.animation_berry, R.drawable.animation_avo, R.drawable.animation_banana, R.drawable.animation_mushroom};
    MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        r1 = (RecyclerView)findViewById(R.id.myRecycler);
        s1 =getResources().getStringArray(R.array.haachama_cooking);
        s2 =getResources().getStringArray(R.array.price);
        s3 =getResources().getStringArray(R.array.description);
        adapter = new MenuAdapter(this, s1, s2, s3, imageResource);

        r1.setAdapter(adapter);
        r1.setLayoutManager(new LinearLayoutManager(this));

    }
}