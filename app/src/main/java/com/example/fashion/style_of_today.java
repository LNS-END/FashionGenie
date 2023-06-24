package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class style_of_today extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_of_today);

        ImageButton imageButton = (ImageButton) findViewById(R.id.MainBack);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(style_of_today.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButtonNext = (ImageButton)findViewById(R.id.next);
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Service service = RetrofitClientInstance.getRetrofitInstance().create(Service.class);

                // Create a new Question object
                Question questionObject = new Question("Recommend outfits with previously give informations.");

                // Now, we don't need MessageContent or ChatMessage
                Call<ResponseBody> call = service.postChatMessage(questionObject);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            try {
                                String fullResponse = response.body().string();
                                JSONObject json = new JSONObject(fullResponse);
                                if (json.has("content")) {
                                    String message = json.getString("content");
                                    Log.d("Content", message);
                                    TextView answerTextView = (TextView) findViewById(R.id.answer);
                                    // Update the TextView
                                    answerTextView.setText(message);

                                    // Show success message
                                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            // Show error message
                            Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        // Handle request failure here
                        Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
    }
}


    //ImageView topClothes = findViewById(R.id.top_clothes);
    //ImageView bottomClothes = findViewById(R.id.bottom_clothes);
    //ImageView shoes = findViewById(R.id.shoes);

// 정보를 받은 후에 이미지 변경
    ///topClothes.setImageResource(R.drawable.new_top_image);
    ///bottomClothes.setImageResource(R.drawable.new_bottom_image);
    ///shoes.setImageResource(R.drawable.new_shoes_image);
