package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MyclosetActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private Button plus, like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycloset);

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyclosetActivity.this, TagsActivity.class);
                startActivity(intent);
            }
        });

        plus=findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyclosetActivity.this, CameraActivity.class);
                startActivity(intent); // 액티비티 이동
            }
        });

        like=findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyclosetActivity.this, StyleActivity.class);
                startActivity(intent);
            }
        });
    }
}