package com.example.fashion;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSIONS = 10;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA};

    private PreviewView previewView;
    private Button btnTakePhoto;

    private ExecutorService cameraExecutor;
    private ImageCapture imageCapture;

    private RadioButton topRadioButton;
    private RadioButton pantsRadioButton;
    private RadioButton shoesRadioButton;
    private RadioButton outerRadioButton;
    private RadioButton headwearRadioButton;
    private RadioButton bagRadioButton;

    private Executor executor = Executors.newSingleThreadExecutor(); // executor 선언

    private static final String TAG = "MainActivity"; // TAG 선언


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        previewView = findViewById(R.id.preview_view);
        btnTakePhoto = findViewById(R.id.btn_take_photo);

        // 카메라 권한 요청
        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        topRadioButton = findViewById(R.id.radio_top);
        pantsRadioButton = findViewById(R.id.radio_pants);
        shoesRadioButton = findViewById(R.id.radio_shoes);
        outerRadioButton = findViewById(R.id.radio_outer);
        headwearRadioButton = findViewById(R.id.radio_headwear);
        bagRadioButton = findViewById(R.id.radio_bag);

        cameraExecutor = Executors.newSingleThreadExecutor();
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                    Preview preview = new Preview.Builder().build();
                    CameraSelector cameraSelector = new CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build();

                    imageCapture = new ImageCapture.Builder().build();

                    preview.setSurfaceProvider(previewView.getSurfaceProvider());

                    Camera camera = cameraProvider.bindToLifecycle(CameraActivity.this, cameraSelector, preview, imageCapture);

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void takePhoto() {
        if (imageCapture == null) {
            return;
        }

        // 이미지 캡처
        imageCapture.takePicture(executor, new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy image) {
                // 이미지를 처리하는 작업 수행
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);

                // 버퍼에 저장된 이미지 사용 가능
                // 여기서 원하는 작업을 수행할 수 있습니다.

                // 이미지를 저장할 경로와 파일명을 생성합니다.
                String imagePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/";

                // 선택한 라디오 버튼을 확인하여 카테고리를 결정합니다.
                String category;
                if (topRadioButton.isChecked()) {
                    category = "top";
                } else if (pantsRadioButton.isChecked()) {
                    category = "pants";
                } else if (shoesRadioButton.isChecked()) {
                    category = "shoes";
                } else if (outerRadioButton.isChecked()){
                    category = "outer";
                } else if (headwearRadioButton.isChecked()) {
                    category = "headwear";
                } else if (bagRadioButton.isChecked()) {
                    category = "bag";
                } else {
                    // 선택된 라디오 버튼이 없는 경우 기본 카테고리를 설정하거나 에러 처리를 수행합니다.
                    //category = "default";
                    // 또는 에러 메시지를 표시할 수 있습니다.
                    Log.e(TAG, "카테고리가 설정되지 않았습니다.");
                    return;
                }

                String fileName = category + "_" + System.currentTimeMillis() + ".jpg";
                imagePath += fileName;

                // 이미지를 저장합니다.
                FileOutputStream output = null;
                try {
                    output = new FileOutputStream(imagePath);
                    output.write(data);
                    output.close();
                    Log.d(TAG, "Image saved successfully: " + imagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Failed to save image: " + e.getMessage());
                }

                // 저장된 이미지의 경로 및 카테고리를 사용하여 원하는 작업을 수행합니다.
                // 예를 들어, 저장된 이미지를 다른 곳에서 표시하거나 데이터베이스에 저장할 수 있습니다.

                image.close();
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                // 이미지 캡처 중 오류 발생
                Log.e(TAG, "Image capture failed: " + exception.getMessage());
            }
        });
    }


    private File createPhotoFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + ".jpg";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(storageDir, imageFileName);
    }
}