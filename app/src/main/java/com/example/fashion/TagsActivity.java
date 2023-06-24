package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TagsActivity extends AppCompatActivity {

    private Button btn_top, btn_pants, btn_outer, btn_shoes, btn_headwear, btn_bag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tags);

        btn_top=findViewById(R.id.btn_top);
        btn_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TagsActivity.this, TopActivity.class);
                startActivity(intent);
            }
        });

        btn_pants=findViewById(R.id.btn_pants);
        btn_pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TagsActivity.this, PantsActivity.class);
                startActivity(intent);
            }
        });

        btn_outer=findViewById(R.id.btn_outer);
        btn_outer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TagsActivity.this, OuterActivity.class);
                startActivity(intent);
            }
        });

        btn_shoes=findViewById(R.id.btn_shoes);
        btn_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TagsActivity.this, ShoesActivity.class);
                startActivity(intent);
            }
        });

        btn_headwear=findViewById(R.id.btn_headwear);
        btn_headwear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TagsActivity.this, HeadwearActivity.class);
                startActivity(intent);
            }
        });

        btn_bag=findViewById(R.id.btn_bag);
        btn_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TagsActivity.this, BagActivity.class);
                startActivity(intent);
            }
        });
    }
}