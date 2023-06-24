package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StyleActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageAdapter imageAdapter;
    private List<String> imagePaths;
    private Button plus;
    private Button delete_btn;
    private Set<Integer> selectedPositions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.style);

        gridView = findViewById(R.id.grid_view);
        imageAdapter = new ImageAdapter(this);

        // 내부 저장소의 이미지 파일 경로를 가져오는 함수 호출
        imagePaths = getImagePaths();

        // 이미지 어댑터에 이미지 파일 경로 리스트 설정
        imageAdapter.setImagePaths(imagePaths);

        // 그리드 뷰에 어댑터 설정
        gridView.setAdapter(imageAdapter);

        plus=findViewById(R.id.plus);
        /*plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StyleActivity.this, TestActivity.class);
                startActivity(intent); // 액티비티 이동
            }
        });*/

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
                if (file.isFile() && file.getName().contains("style_")) {
                    paths.add(file.getAbsolutePath());
                }
            }
        } else {
            files = new File[0]; // 빈 파일 배열로 초기화
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