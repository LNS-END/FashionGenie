package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HeadwearActivity extends AppCompatActivity {
    private GridView gridView;
    private ImageAdapter imageAdapter;
    private List<String> imagePaths;
    private Button plus;
    private Button delete_btn;

    private Set<Integer> selectedPositions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headwear);

        plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeadwearActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        gridView = findViewById(R.id.grid_view);
        imageAdapter = new ImageAdapter(this);

        imagePaths = getImagePaths();

        imageAdapter.setImagePaths(imagePaths);

        gridView.setAdapter(imageAdapter);

        selectedPositions = new HashSet<>();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectedPositions.contains(position)) {
                    selectedPositions.remove(position);
                    view.setBackgroundResource(android.R.color.transparent);
                } else {
                    selectedPositions.add(position);
                    view.setBackgroundResource(R.drawable.selected_item);
                }
            }
        });

        delete_btn = findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedImages();
            }
        });
    }

    private List<String> getImagePaths() {
        List<String> paths = new ArrayList<>();
        String directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Android/data/com.example.fashion/files/Pictures/";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().contains("headwear_")) {
                    paths.add(file.getAbsolutePath());
                }
            }
        } else {
            files = new File[0];
        }
        return paths;
    }

    private void deleteSelectedImages() {
        List<String> pathsToRemove = new ArrayList<>();

        for (int position : selectedPositions) {
            if (position >= 0 && position < imagePaths.size()) {
                pathsToRemove.add(imagePaths.get(position));
            }
        }

        for (String path : pathsToRemove) {
            File imageFile = new File(path);
            if (imageFile.exists() && imageFile.delete()) {
                imagePaths.remove(path);
            }
        }

        selectedPositions.clear();

        imageAdapter.setImagePaths(imagePaths);
        imageAdapter.notifyDataSetChanged();

        Toast.makeText(this, "이미지가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
