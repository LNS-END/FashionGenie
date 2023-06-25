package com.example.fashion;

import android.app.Application;
import retrofit2.Call;
import android.util.Log;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import androidx.annotation.NonNull;

import com.example.fashion.MyAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MyApplication extends Application {

    private ExecutorService executorService;

    @Override
    public void onCreate() {
        super.onCreate();

        executorService = Executors.newSingleThreadExecutor();
        loadInitialData();
    }


    private void loadInitialData() {
        executorService.execute(() -> {
            // Prepare your Retrofit instance
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            // Prepare your Retrofit instance
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://wasincome.run.goorm.site/v1data/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();


            MyAPI api = retrofit.create(MyAPI.class);

            // Get a reference to your database
            com.example.fashion.DataBase database = com.example.fashion.DataBase.getInstance(getApplicationContext());

            List<com.example.fashion.Bag> bag = database.bagDao().getBagAll();
            List<com.example.fashion.HeadWear> headWear = database.headWearDao().getHeadWearAll();
            List<com.example.fashion.Outer> outers = database.outerDao().getOuterAll();
            List<Pants> pants = database.pantsDao().getPantsAll();
            List<com.example.fashion.Shoes> shoes = database.shoesDao().getShoesAll();
            List<com.example.fashion.Top> tops = database.topDao().getTopAll();

            sendAllDataToServer(api, bag, headWear, outers, pants, shoes, tops);
        });
    }

    private void sendAllDataToServer(MyAPI api, List<com.example.fashion.Bag> bags, List<com.example.fashion.HeadWear> headWears, List<com.example.fashion.Outer> outers
    , List<com.example.fashion.Pants> pants, List<com.example.fashion.Shoes> shoes, List<com.example.fashion.Top> tops) {
        // For bags
        api.sendBagData(bags).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,@NonNull Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Bags sent successfully");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Handle the failure...
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.e("MyAPI", "Network error", t);
                } else {
                    // Some other kind of error happened
                    Log.e("MyAPI", "Some other error happened", t);
                }
            }
        });

        // Repeat for all your item types... in this case, HeadWear
        api.sendHeadwearData(headWears).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "HeadWears sent successfully");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Handle the failure...
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.e("MyAPI", "Network error", t);
                } else {
                    // Some other kind of error happened
                    Log.e("MyAPI", "Some other error happened", t);
                }
            }
        });

        api.sendOuterData(outers).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Bags sent successfully");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call,@NonNull  Throwable t) {
                // Handle the failure...
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.e("MyAPI", "Network error", t);
                } else {
                    // Some other kind of error happened
                    Log.e("MyAPI", "Some other error happened", t);
                }
            }
        });

        api.sendPantsData(pants).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Bags sent successfully");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call,@NonNull  Throwable t) {
                // Handle the failure...
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.e("MyAPI", "Network error", t);
                } else {
                    // Some other kind of error happened
                    Log.e("MyAPI", "Some other error happened", t);
                }
            }
        });

        api.sendShoesData(shoes).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Bags sent successfully");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Handle the failure...
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.e("MyAPI", "Network error", t);
                } else {
                    // Some other kind of error happened
                    Log.e("MyAPI", "Some other error happened", t);
                }
            }
        });
        api.sendTopData(tops).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Bags sent successfully");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Handle the failure...
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.e("MyAPI", "Network error", t);
                } else {
                    // Some other kind of error happened
                    Log.e("MyAPI", "Some other error happened", t);
                }
            }
        });
    }
}
