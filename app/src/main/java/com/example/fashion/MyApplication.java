package com.example.fashion;

import android.app.Application;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize and load your data here
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadInitialData();
            }
        }).start();
    }

    private void loadInitialData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://wasincome.run.goorm.site/v1data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI api = retrofit.create(MyAPI.class);

        // For each class of item (Bag, HeadWear, Outer, Pants, Shoes, Top, Coordi, LikedCoordi)
        List<Bag> bagList = new ArrayList<>();
        Bag bag = new Bag();

        bag.setID(1);
        bag.setImagePath("NULL");
        bag.setCategory("Sports");
        bag.setBrand("Nike");
        bag.setSeason("Summer");
        bag.setStyle("Sports");
        bag.setTextile("soft");
        bag.setColor("red");
        bagList.add(bag);

        api.sendBagData(bagList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Request succeeded");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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
        List<HeadWear> headWearList = new ArrayList<>();
        HeadWear headwear = new HeadWear();
        // Initialize the headwear...
        headwear.setID(1);
        headwear.setImagePath("NULL");
        headwear.setCategory("Sports");
        headwear.setBrand("Nike");
        headwear.setSeason("Summer");
        headwear.setStyle("Sports");
        headwear.setTextile("soft");
        headwear.setColor("red");
        headWearList.add(headwear);

        api.sendHeadwearData(headWearList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Request succeeded");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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
        List<com.example.fashion.Outer> outerList = new ArrayList<>();
        com.example.fashion.Outer outer  = new com.example.fashion.Outer();
        // Initialize the outer...
        outer.setID(1);
        outer.setImagePath("NULL");
        outer.setCategory("Sports");
        outer.setBrand("Nike");
        outer.setSeason("Summer");
        outer.setStyle("Sports");
        outer.setTextile("soft");
        outer.setColor("red");
        outerList.add(outer);

        api.sendOuterData(outerList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Request succeeded");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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
        List<Pants> pantsList = new ArrayList<>();
        Pants pants = new Pants();
        // Initialize the pants...
        pants.setID(1);
        pants.setImagePath("NULL");
        pants.setCategory("Sports");
        pants.setBrand("Nike");
        pants.setSeason("Summer");
        pants.setStyle("Sports");
        pants.setTextile("soft");
        pants.setColor("red");
        pantsList.add(pants);

        api.sendPantsData(pantsList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Request succeeded");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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
        List<com.example.fashion.Shoes> shoesList = new ArrayList<>();
        com.example.fashion.Shoes shoes  = new com.example.fashion.Shoes();
        // Initialize the shoes...
        shoes.setID(1);
        shoes.setImagePath("NULL");
        shoes.setCategory("Sports");
        shoes.setBrand("Nike");
        shoes.setSeason("Summer");
        shoes.setStyle("Sports");
        shoes.setTextile("soft");
        shoes.setColor("red");
        shoesList.add(shoes);

        api.sendShoesData(shoesList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Request succeeded");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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
        List<com.example.fashion.Top> topList = new ArrayList<>();
        com.example.fashion.Top top  = new com.example.fashion.Top();
        // Initialize the top...
        top.setID(1);
        top.setImagePath("NULL");
        top.setCategory("Sports");
        top.setBrand("Nike");
        top.setSeason("Summer");
        top.setStyle("Sports");
        top.setTextile("soft");
        top.setColor("red");
        topList.add(top);

        api.sendTopData(topList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle the response...
                if (response.isSuccessful()) {
                    Log.e("MyAPI", "Request succeeded");
                } else {
                    Log.e("MyAPI", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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

//        Coordi coordi   = new Coordi();
        // Initialize the top...
//        api.sendCoordiData(coordi).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle the response...
//                if (response.isSuccessful()) {
//                    Log.e("MyAPI", "Request succeeded");
//                } else {
 //                   Log.e("MyAPI", "Server returned an error: " + response.code());
 //               }
 //           }

//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
                // Handle the failure...
//                if (t instanceof IOException) {
                    // A network or conversion error happened
//                    Log.e("MyAPI", "Network error", t);
 //               } else {
                    // Some other kind of error happened
 //                   Log.e("MyAPI", "Some other error happened", t);
 //               }
 //           }
 //       });

//        LikedCoordi likedCoordi   = new LikedCoordi();
        // Initialize the top...
 //       api.sendLikedCoordiData(likedCoordi).enqueue(new Callback<Void>() {
 //           @Override
 //           public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle the response...
 //               if (response.isSuccessful()) {
 //                   Log.e("MyAPI", "Request succeeded");
  //              } else {
  //                  Log.e("MyAPI", "Server returned an error: " + response.code());
 //               }
 //           }

 //           @Override
  //          public void onFailure(Call<Void> call, Throwable t) {
                // Handle the failure...
  //              if (t instanceof IOException) {
                    // A network or conversion error happened
  //                  Log.e("MyAPI", "Network error", t);
  //              } else {
                    // Some other kind of error happened
 //                   Log.e("MyAPI", "Some other error happened", t);
  //              }
 //           }
//        });
    }
}