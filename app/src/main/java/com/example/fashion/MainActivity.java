package com.example.fashion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements com.example.fashion.DatabaseInitializationTask.DatabaseInitializationListener {

    private com.example.fashion.HeadWearDao mHeadWearDao;
    private com.example.fashion.BagDao mBagDao;
    private com.example.fashion.OuterDao mOuterDao;
    private com.example.fashion.PantsDao mPantsDao;
    private com.example.fashion.ShoesDao mShoesDao;
    private com.example.fashion.TopDao mTopDao;
    private com.example.fashion.CoordiDao mcoordiDao;
    private com.example.fashion.WeatherDao mWeatherDao;
    private com.example.fashion.LikedCoordiDao mLikedCoordiDao;
    private Button btn_Style;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_Style = findViewById(R.id.todayOf_style);
        btn_Style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,style_of_today.class);
                startActivity(intent); // 엑티비티 이동
            }
        });
        com.example.fashion.DatabaseInitializationTask initializationTask = new com.example.fashion.DatabaseInitializationTask(this, this);
        initializationTask.execute();


    }

    @Override
    public void onDatabaseInitialized(com.example.fashion.DataBase database) throws JSONException {
        mHeadWearDao = database.headWearDao();
        mBagDao = database.bagDao();
        mPantsDao = database.pantsDao();
        mTopDao = database.topDao();
        mShoesDao = database.shoesDao();
        mOuterDao = database.outerDao();
        mcoordiDao = database.coordiDao();
        mLikedCoordiDao = database.likedCoordiDao();
        mWeatherDao = database.weatherDao();

//        InsertBagTask insertBagTask = new InsertBagTask(mBagDao);
//        insertBagTask.execute();

//        int pantsIdToSelect = 1;
//        SelectPantsTask selectPantsTask = new SelectPantsTask(mPantsDao);
//        selectPantsTask.execute(pantsIdToSelect);

//        InsertPantsTask insertPantsTask = new InsertPantsTask(mPantsDao);
//        insertPantsTask.execute();





//        InsertTopTask insertTopTask = new InsertTopTask(mTopDao);
//        insertTopTask.execute();
//
//        InsertBagTask insertBagTask = new InsertBagTask(mBagDao);
//        insertBagTask.execute();
//
//        InsertPantsTask insertPantsTask = new InsertPantsTask(mPantsDao);
//        insertPantsTask.execute();
//
//        InsertShoesTask insertShoesTask = new InsertShoesTask(mShoesDao);
//        insertShoesTask.execute();
//
//        InsertHeadWearTask insertHeadWearTask = new InsertHeadWearTask(mHeadWearDao);
//        insertHeadWearTask.execute();
//
//        InsertOuterTask insertOuterTask = new InsertOuterTask(mOuterDao);
//        insertOuterTask.execute();
//


        // 데이터 삽입 작업을 백그라운드 스레드에서 실행
        // InsertHeadWearTask insertHeadWearTask = new InsertHeadWearTask(mHeadWearDao);
        // topToUpdate.setID(1);
        // topToUpdate.setColor("blue");// 수정할 Top 객체
        // UpdateTopTask updateTopTask = new UpdateTopTask(mTopDao, topToUpdate);
        // updateTopTask.execute();

        //insertHeadWearTask.execute();

//        HeadWear headWearToUpdate = new HeadWear();

//        headWearToUpdate.setID(1);
//        headWearToUpdate.setColor("Blue");
//        UpdateHeadWearTask updateHeadWearTask = new UpdateHeadWearTask(mHeadWearDao,headWearToUpdate);
//        updateHeadWearTask.execute();


        //InsertTopTask insertTopTask = new InsertTopTask(mTopDao);
        //insertTopTask.execute();

        // Top toptoDelete = new Top();
        // toptoDelete.setID(1);
        // DeleteTopTask deleteTopTask = new DeleteTopTask(mTopDao, toptoDelete);
        // deleteTopTask.execute();

        // 데이터 수정 작업을 백그라운드 스레드에서 실행
        //  Top topToUpdate = new Top();
        // 데이터 삭제 작업을 백그라운드 스레드에서 실행
//        Pants pantsToDelete = new Pants();// 삭제할 Pants 객체
//        pantsToDelete.setID(3);
//        DeletePantsTask deletePantsTask = new DeletePantsTask(mPantsDao, pantsToDelete);
//        deletePantsTask.execute();

//        Outer outerToDelete = new Outer();
//        outerToDelete.setID(2);
//        DeleteOuterTask deleteOuterTask = new DeleteOuterTask(mOuterDao,outerToDelete);
//        deleteOuterTask.execute();

//        Top TopTODelete = new Top();
//        TopTODelete.setID(5);
//        DeleteTopTask deleteTopTask = new DeleteTopTask(mTopDao,TopTODelete);
//        deleteTopTask.execute();


        // topToUpdate.setID(1);
        // topToUpdate.setColor("blue");// 수정할 Top 객체
        // UpdateTopTask updateTopTask = new UpdateTopTask(mTopDao, topToUpdate);
        // updateTopTask.execute();
//        InsertWeatherTask insertWeatherTask = new InsertWeatherTask(mWeatherDao);
//        insertWeatherTask.execute();
//        Pants pantsTODelete = new Pants();
//        pantsTODelete.setID(2);
//        DeletePantsTask deletePantsTask = new DeletePantsTask(mPantsDao,pantsTODelete);
//        deletePantsTask.execute();




    }
    private static class InsertWeatherTask extends AsyncTask<Void,Void,Void>{
        private com.example.fashion.WeatherDao mWeatherDao;
        public InsertWeatherTask(com.example.fashion.WeatherDao weatherDao){
            mWeatherDao = weatherDao;
        }
        @Override
        protected  Void doInBackground(Void...voids){
            com.example.fashion.Weather weather = new com.example.fashion.Weather();
            mWeatherDao.setInsertWeather(weather);
            return null;
        }
    }
    private static class DeleteWeatherTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.WeatherDao mWeatherDao;
        private com.example.fashion.Weather mWeather;

        public DeleteWeatherTask(com.example.fashion.WeatherDao weatherDao, com.example.fashion.Weather weather) {
            mWeatherDao =weatherDao;
            mWeather= weather;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWeatherDao.setDeleteWeather(mWeather);
            return null;
        }
    }
    private static class UpdateWeatherTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.WeatherDao mWeatherDao;
        private com.example.fashion.Weather mWeather;

        public UpdateWeatherTask(com.example.fashion.WeatherDao weatherDao, com.example.fashion.Weather weather) {
            mWeatherDao =weatherDao;
            mWeather =weather;
        }
        @Override
        protected Void doInBackground(Void... voids){
            mWeatherDao.setUpdateWeather(mWeather);
            return null;
        }
    }
    private static class SelectWeatherTask extends AsyncTask<Integer, Void, com.example.fashion.Weather> {
        private com.example.fashion.WeatherDao mWeatherDao;

        public SelectWeatherTask(com.example.fashion.WeatherDao weatherDao) {
            mWeatherDao = weatherDao;
        }

        @Override
        protected com.example.fashion.Weather doInBackground(Integer... ids) {
            return mWeatherDao.getWeatherById(ids[0]);
        }

        @Override
        protected void onPostExecute(com.example.fashion.Weather weather) {
            super.onPostExecute(weather);
            if (weather != null) {
                Float weatherTemperature = weather.getTemperature();
                Float weatherHuminity = weather.getHumidity();
                Float weatherWindSpeed = weather.getWindSpeed();
                String weatherCondition = weather.getWeatherCondition();

                Log.d("SelectWeatherTask", "Selected Weather Temperature: " + weatherTemperature);
                Log.d("SelectWeatherTask", "Selected Weather Humidity: " + weatherHuminity);
                Log.d("SelectWeatherTask", "Selected Weather WindSpeed: " + weatherWindSpeed);
                Log.d("SelectWeatherTask", "Selected Weather Condition: " + weatherCondition);

            } else {
                Log.d("SelectWeatherTask", "No weather found with given ID");
            }
        }
    }
    private static class InsertPantsTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.PantsDao mPantsDao;
        public InsertPantsTask(com.example.fashion.PantsDao pantsDao) {
            mPantsDao = pantsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            com.example.fashion.Pants pants = new com.example.fashion.Pants();
            pants.setSeason("Summer");
            pants.setBrand("Adidas");
            pants.setColor("Red");
            pants.setCategory("Gym");
            pants.setImagePath("");
            pants.setStyle("casual");
            pants.setTextile("soft");
            pants.setImagePath("NULL");
            mPantsDao.setInsertPants(pants);
            return null;
        }
    }
    private static class DeletePantsTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.PantsDao mPantsDao;
        private com.example.fashion.Pants mPants;

        public DeletePantsTask(com.example.fashion.PantsDao pantsDao, com.example.fashion.Pants pants) {
            mPantsDao = pantsDao;
            mPants = pants;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mPantsDao.setDeletePants(mPants);
            return null;
        }
    }
    private static class UpdatePantsTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.PantsDao mPantsDao;
        private com.example.fashion.Pants mPants;

        public UpdatePantsTask(com.example.fashion.PantsDao pantsDao, com.example.fashion.Pants pants) {
            mPantsDao = pantsDao;
            mPants = pants;
        }
        @Override
        protected Void doInBackground(Void... voids){
            mPantsDao.setUpdatePants(mPants);
            return null;
        }
    }
    private static class SelectPantsTask extends AsyncTask<Integer, Void, com.example.fashion.Pants> {
        private ImageView mImageView;
        private Context mContext;
        private com.example.fashion.PantsDao mPantsDao;


        public SelectPantsTask(com.example.fashion.PantsDao pantsDao) {
            mPantsDao = pantsDao;
//            mContext = context;
//            mImageView = imageView;
        }

        @Override
        protected com.example.fashion.Pants doInBackground(Integer... ids) {
            return mPantsDao.getPantsById(ids[0]);
        }

        @Override
        protected void onPostExecute(com.example.fashion.Pants pants) {
            super.onPostExecute(pants);
            if (pants != null) {
                String pantsBrand = pants.getBrand();
                String pantsCategory = pants.getCategory();
                String pantsColor = pants.getColor();
                String pantsTextile = pants.getTextile();
                String pantsSeason = pants.getSeason();
                String pantsStyle = pants.getStyle();
                String pantsImagePath = pants.getImagePath();

                Log.d("SelectPantsTask", "Selected Pants Brand: " + pantsBrand);
                Log.d("SelectPantsTask", "Selected Pants Category: " + pantsCategory);
                Log.d("SelectPantsTask", "Selected Pants Color: " + pantsColor);
                Log.d("SelectPantsTask", "Selected Pants Textile: " + pantsTextile);
                Log.d("SelectPantsTask", "Selected Pants Season: " + pantsSeason);
                Log.d("SelectPantsTask", "Selected Pants Style: " + pantsStyle);
                Log.d("SelectPantsTask","Selected Pants ImagePath: "+ pantsImagePath);

//                Glide.with(mContext)
//                        .load(new File(pantsImagePath))
//                        .into(mImageView);
            }
            else {
                Log.d("SelectPantsTask", "No pants found with given ID");
            }
        }
    }
    private static class DeleteTopTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.TopDao mTopDao;
        private com.example.fashion.Top mTop;

        public DeleteTopTask(com.example.fashion.TopDao topDao, com.example.fashion.Top top) {
            mTopDao = topDao;
            mTop = top;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mTopDao.setDeleteTop(mTop);
            return null;
        }
    }
    private static class InsertTopTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.TopDao mTopDao;

        public InsertTopTask(com.example.fashion.TopDao topDao) {
            mTopDao = topDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            com.example.fashion.Top top = new com.example.fashion.Top();
            top.setSeason("Summer");
            top.setBrand("Adidas");
            top.setColor("Red");
            top.setCategory("Gym");
            top.setImagePath("");
            top.setStyle("casual");
            top.setTextile("soft");
            top.setImagePath("NULL");
            mTopDao.setInsertTop(top);
            return null;
        }
    }
    private static class UpdateTopTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.TopDao mTopDao;
        private com.example.fashion.Top mTop;

        public UpdateTopTask(com.example.fashion.TopDao topDao, com.example.fashion.Top top) {
            mTopDao = topDao;
            mTop = top;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mTopDao.setUpdateTop(mTop);
            return null;
        }
    }
    private static class SelectTopTask extends AsyncTask<Integer, Void, com.example.fashion.Top> {
        private com.example.fashion.TopDao mTopDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectTopTask(com.example.fashion.TopDao topDao) {
            mTopDao = topDao;
//            mImageView = imageView;
//            mContext = context;
        }

        @Override
        protected com.example.fashion.Top doInBackground(Integer... ids) {
            return mTopDao.getTopById(ids[0]);
        }

        @Override
        protected void onPostExecute(com.example.fashion.Top top) {
            super.onPostExecute(top);
            if (top != null) {
                String topBrand = top.getBrand();
                String topCategory = top.getCategory();
                String topColor = top.getColor();
                String topTextile = top.getTextile();
                String topSeason = top.getSeason();
                String topStyle = top.getStyle();
                String TopImagePath = top.getImagePath();

                Log.d("SelectTopTask", "Selected Top Brand: " + topBrand);
                Log.d("SelectTopTask", "Selected Top Category: " + topCategory);
                Log.d("SelectTopTask", "Selected Top Color: " + topColor);
                Log.d("SelectTopTask", "Selected Top Textile: " + topTextile);
                Log.d("SelectTopTask", "Selected Top Season: " + topSeason);
                Log.d("SelectTopTask", "Selected Top Style: " + topStyle);
//                Glide.with(mContext)
//                        .load(new File(TopImagePath))
//                        .into(mImageView);
            } else {
                Log.d("SelectTopTask", "No top found with given ID");
            }
        }
    }
    private static class InsertOuterTask extends AsyncTask<com.example.fashion.Outer, Void, Void> {
        private com.example.fashion.OuterDao mOuterDao;

        public InsertOuterTask(com.example.fashion.OuterDao outerDao) {
            mOuterDao = outerDao;
        }

        @Override
        protected Void doInBackground(com.example.fashion.Outer... outers) {
            com.example.fashion.Outer outer = new com.example.fashion.Outer();
            outer.setSeason("Summer");
            outer.setBrand("Adidas");
            outer.setColor("Red");
            outer.setCategory("Gym");
            outer.setImagePath("");
            outer.setStyle("casual");
            outer.setTextile("soft");
            outer.setImagePath("NULL");
            mOuterDao.setInsertOuter(outer);
            return null;
        }
    }
    private static class DeleteOuterTask extends AsyncTask<com.example.fashion.Outer, Void, Void> {
        private com.example.fashion.OuterDao mOuterDao;
        private com.example.fashion.Outer mOuter;

        public DeleteOuterTask(com.example.fashion.OuterDao outerDao, com.example.fashion.Outer outer) {
            mOuterDao = outerDao;
            mOuter = outer;
        }

        @Override
        protected Void doInBackground(com.example.fashion.Outer... outers) {
            mOuterDao.setDeleteOuter(mOuter);
            return null;
        }
    }
    private static class UpdateOuterTask extends AsyncTask<com.example.fashion.Outer, Void, Void> {
        private com.example.fashion.OuterDao mOuterDao;
        private com.example.fashion.Outer mOuter;

        public UpdateOuterTask(com.example.fashion.OuterDao outerDao, com.example.fashion.Outer outer) {
            mOuterDao = outerDao;
            mOuter = outer;
        }

        @Override
        protected Void doInBackground(com.example.fashion.Outer... outer) {
            mOuterDao.setUpdateOuter(mOuter);
            return null;
        }
    }
    private static class SelectOuterTask extends AsyncTask<Integer, Void, com.example.fashion.Outer> {
        private com.example.fashion.OuterDao mOuterDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectOuterTask(com.example.fashion.OuterDao outerDao) {
            mOuterDao = outerDao;
//            mContext = context;
//            mImageView = imageView;
        }

        @Override
        protected com.example.fashion.Outer doInBackground(Integer... ids) {
            return mOuterDao.getOuterById(ids[0]);
        }

        @Override
        protected void onPostExecute(com.example.fashion.Outer outer) {
            super.onPostExecute(outer);
            if (outer != null) {
                String outerBrand = outer.getBrand();
                String outerCategory = outer.getCategory();
                String outerColor = outer.getColor();
                String outerTextile = outer.getTextile();
                String outerSeason = outer.getSeason();
                String outerStyle = outer.getStyle();
                String outerImagePath = outer.getImagePath();

                Log.d("SelectOuterTask", "Selected Outer Brand: " + outerBrand);
                Log.d("SelectOuterTask", "Selected Outer Category: " + outerCategory);
                Log.d("SelectOuterTask", "Selected Outer Color: " + outerColor);
                Log.d("SelectOuterTask", "Selected Outer Textile: " + outerTextile);
                Log.d("SelectOuterTask", "Selected Outer Season: " + outerSeason);
                Log.d("SelectOuterTask", "Selected Outer Style: " + outerStyle);
//                Glide.with(mContext)
//                        .load(new File(outerImagePath))
//                        .into(mImageView);
            } else {
                Log.d("SelectOuterTask", "No Outer found with given ID");
            }
        }
    }
    private static class InsertShoesTask extends AsyncTask<com.example.fashion.Shoes, Void, Void> {
        private com.example.fashion.ShoesDao mShoesDao;

        public InsertShoesTask(com.example.fashion.ShoesDao shoesDao) {
            mShoesDao = shoesDao;
        }

        @Override
        protected Void doInBackground(com.example.fashion.Shoes... shoeses) {
            com.example.fashion.Shoes shoes = new com.example.fashion.Shoes();
            shoes.setSeason("Summer");
            shoes.setBrand("Adidas");
            shoes.setColor("Red");
            shoes.setCategory("Gym");
            shoes.setImagePath("");
            shoes.setStyle("casual");
            shoes.setTextile("soft");
            shoes.setImagePath("NULL");
            mShoesDao.setInsertShoes(shoes);
            return null;
        }
    }
    private static class DeleteShoesTask extends AsyncTask<com.example.fashion.Shoes, Void, Void> {
        private com.example.fashion.ShoesDao mShoesDao;
        private com.example.fashion.Shoes mShoes;

        public DeleteShoesTask(com.example.fashion.ShoesDao shoesDao, com.example.fashion.Shoes shoes) {
            mShoesDao =shoesDao;
            mShoes = shoes;
        }

        @Override
        protected Void doInBackground(com.example.fashion.Shoes... shoes) {
            mShoesDao.setDeleteShoes(mShoes);
            return null;
        }
    }
    private static class UpdateShoesTask extends AsyncTask<com.example.fashion.Shoes, Void, Void> {
        private com.example.fashion.ShoesDao mShoesDao;
        private com.example.fashion.Shoes mShoes;

        public UpdateShoesTask(com.example.fashion.ShoesDao shoesDao, com.example.fashion.Shoes shoes) {
            mShoesDao =shoesDao;
            mShoes = shoes;
        }

        @Override
        protected Void doInBackground(com.example.fashion.Shoes... shoes) {
            mShoesDao.setUpdateShoes(mShoes);
            return null;
        }
    }
    private static class SelectShoesTask extends AsyncTask<Integer, Void, com.example.fashion.Shoes> {
        private com.example.fashion.ShoesDao mShoesDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectShoesTask(com.example.fashion.ShoesDao shoesDao) {
            mShoesDao = shoesDao;
//            mContext = context;
//            mImageView = imageView;
        }

        @Override
        protected com.example.fashion.Shoes doInBackground(Integer... ids) {
            return mShoesDao.getShoesById(ids[0]);
        }

        @Override
        protected void onPostExecute(com.example.fashion.Shoes shoes) {
            super.onPostExecute(shoes);
            if (shoes != null) {
                String shoesBrand = shoes.getBrand();
                String shoesCategory = shoes.getCategory();
                String shoesColor = shoes.getColor();
                String shoesTextile = shoes.getTextile();
                String shoesSeason = shoes.getSeason();
                String shoesStyle = shoes.getStyle();
                String shoesImagePath = shoes.getImagePath();

                Log.d("SelectShoesTask", "Selected Shoes Brand: " + shoesBrand);
                Log.d("SelectShoesTask", "Selected Shoes Category: " + shoesCategory);
                Log.d("SelectShoesTask", "Selected Shoes Color: " + shoesColor);
                Log.d("SelectShoesTask", "Selected Shoes Textile: " + shoesTextile);
                Log.d("SelectShoesTask", "Selected Shoes Season: " + shoesSeason);
                Log.d("SelectShoesTask", "Selected Shoes Style: " + shoesStyle);
//                Glide.with(mContext)
//                        .load(new File(shoesImagePath))
//                        .into(mImageView);
            } else {
                Log.d("SelectShoesTask", "No Shoes found with given ID");
            }
        }
    }
    private static class InsertBagTask extends AsyncTask<com.example.fashion.Bag, Void, Void> {
        private com.example.fashion.BagDao mBagDao;

        public InsertBagTask(com.example.fashion.BagDao bagDao) {
            mBagDao = bagDao;
        }

        @Override
        protected Void doInBackground(com.example.fashion.Bag... bags) {
            com.example.fashion.Bag bag = new com.example.fashion.Bag();
            bag.setSeason("Summer");
            bag.setBrand("Adidas");
            bag.setColor("Red");
            bag.setCategory("Gym");
            bag.setImagePath("");
            bag.setStyle("casual");
            bag.setTextile("soft");
            bag.setImagePath("NULL");
            mBagDao.setInsertBag(bag);
            return null;
        }
    }
    private static class DeleteBagTask extends AsyncTask<com.example.fashion.Bag, Void, Void> {
        private com.example.fashion.BagDao mBagDao;
        private com.example.fashion.Bag mBag;

        public DeleteBagTask(com.example.fashion.BagDao bagDao, com.example.fashion.Bag bag) {
            mBagDao = bagDao;
            mBag = bag;
        }

        @Override
        protected Void doInBackground(com.example.fashion.Bag... bags) {
            mBagDao.setDeleteBag(mBag);
            return null;
        }
    }
    private static class SelectBagTask extends AsyncTask<Integer, Void, com.example.fashion.Bag> {
        private com.example.fashion.BagDao mBagDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectBagTask(com.example.fashion.BagDao bagDao) {
            mBagDao = bagDao;
//            mContext = context;
//            mImageView = imageView;

        }

        @Override
        protected com.example.fashion.Bag doInBackground(Integer... ids) {
            return mBagDao.getBagById(ids[0]);
        }

        @Override
        protected void onPostExecute(com.example.fashion.Bag bag) {
            super.onPostExecute(bag);
            if (bag != null) {
                String bagBrand = bag.getBrand();
                String bagCategory = bag.getCategory();
                String bagColor = bag.getColor();
                String bagTextile = bag.getTextile();
                String bagSeason = bag.getSeason();
                String bagStyle = bag.getStyle();
                String bagImagePath = bag.getImagePath();

                Log.d("SelectBagTask", "Selected Bag Brand: " + bagBrand);
                Log.d("SelectBagTask", "Selected Bag Category: " + bagCategory);
                Log.d("SelectBagTask", "Selected Bag Color: " + bagColor);
                Log.d("SelectBagTask", "Selected Bag Textile: " + bagTextile);
                Log.d("SelectBagTask", "Selected Bag Season: " + bagSeason);
                Log.d("SelectBagTask", "Selected Bag Style: " + bagStyle);
//                Glide.with(mContext)
//                        .load(new File(bagImagePath))
//                        .into(mImageView);
            } else {
                Log.d("SelectBagTask", "No bag found with given ID");
            }
        }
    }
    private static class UpdateBagTask extends AsyncTask<com.example.fashion.Bag, Void, Void> {
        private com.example.fashion.BagDao mBagDao;
        private com.example.fashion.Bag mBag;

        public UpdateBagTask(com.example.fashion.BagDao bagDao, com.example.fashion.Bag bag) {
            mBagDao = bagDao;
            mBag = bag;
        }

        @Override
        protected Void doInBackground(com.example.fashion.Bag... bags) {
            mBagDao.setUpdateBag(mBag);
            return null;
        }
    }


    private static class InsertHeadWearTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.HeadWearDao mHeadWearDao;

        public InsertHeadWearTask(com.example.fashion.HeadWearDao headWearDao) {
            mHeadWearDao = headWearDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            com.example.fashion.HeadWear headWear = new com.example.fashion.HeadWear();
            headWear.setSeason("Summer");
            headWear.setBrand("Adidas");
            headWear.setColor("Red");
            headWear.setCategory("Gym");
            headWear.setImagePath("");
            headWear.setStyle("casual");
            headWear.setTextile("soft");
            headWear.setImagePath("NULL");
            mHeadWearDao.setInsertHeadWear(headWear);
            return null;
        }
    }
    private static class DeleteHeadWearTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.HeadWearDao mHeadWearDao;
        private com.example.fashion.HeadWear mHeadWear;

        public DeleteHeadWearTask(com.example.fashion.HeadWearDao headWearDao, com.example.fashion.HeadWear headWear) {
            mHeadWearDao = headWearDao;
            mHeadWear = headWear;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mHeadWearDao.setDeleteHeadWear(mHeadWear);
            return null;
        }
    }
    private static class UpdateHeadWearTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.HeadWearDao mHeadWearDao;
        private com.example.fashion.HeadWear mHeadWear;

        public UpdateHeadWearTask(com.example.fashion.HeadWearDao headWearDao, com.example.fashion.HeadWear headWear) {
            mHeadWearDao = headWearDao;
            mHeadWear = headWear;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mHeadWearDao.setUpdateHeadWear(mHeadWear);
            return null;
        }
    }
    private static class SelectHeadWearTask extends AsyncTask<Integer, Void, com.example.fashion.HeadWear> {
        private com.example.fashion.HeadWearDao mHeadWearDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectHeadWearTask(com.example.fashion.HeadWearDao headWearDao) {
            mHeadWearDao = headWearDao;
        }

        @Override
        protected com.example.fashion.HeadWear doInBackground(Integer... ids) {
            return mHeadWearDao.getHeadWearById(ids[0]);
        }

        @Override
        protected void onPostExecute(com.example.fashion.HeadWear headWear) {
            super.onPostExecute(headWear);
            if (headWear != null) {
                String headWearBrand = headWear.getBrand();
                String headWearCategory = headWear.getCategory();
                String headWearColor = headWear.getColor();
                String headWearTextile = headWear.getTextile();
                String headWearSeason = headWear.getSeason();
                String headWearStyle = headWear.getStyle();
                String headWearImagePath = headWear.getImagePath();

                Log.d("SelectHeadWearTask", "Selected HeadWear Brand: " + headWearBrand);
                Log.d("SelectHeadWearTask", "Selected HeadWear Category: " + headWearCategory);
                Log.d("SelectHeadWearTask", "Selected HeadWear Color: " + headWearColor);
                Log.d("SelectHeadWearTask", "Selected HeadWear Textile: " + headWearTextile);
                Log.d("SelectHeadWearTask", "Selected HeadWear Season: " + headWearSeason);
                Log.d("SelectHeadWearTask", "Selected HeadWear Style: " + headWearStyle);

            } else {
                Log.d("SelectHeadWearTask", "No HeadWear found with given ID");
            }
        }
    }
    private static class InsertCoordiTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.CoordiDao mCoordiDao;

        public InsertCoordiTask(com.example.fashion.CoordiDao coordiDao) {
            mCoordiDao = coordiDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            com.example.fashion.Coordi coordi = new com.example.fashion.Coordi();
            mCoordiDao.setInsertCoordi(coordi);
            return null;
        }
    }
    private static class DeleteCoordiTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.CoordiDao mCoordiDao;
        private com.example.fashion.Coordi mCoordi;

        public DeleteCoordiTask(com.example.fashion.CoordiDao coordiDao, com.example.fashion.Coordi coordi) {
            mCoordiDao = coordiDao;
            mCoordi = coordi;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mCoordiDao.setDeleteCoordi(mCoordi);
            return null;
        }
    }
    private static class UpdateCoordiTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.CoordiDao mCoordiDao;
        private com.example.fashion.Coordi mCoordi;

        public UpdateCoordiTask(com.example.fashion.CoordiDao coordiDao, com.example.fashion.Coordi coordi) {
            mCoordiDao = coordiDao;
            mCoordi = coordi;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mCoordiDao.setUpdateCoodi(mCoordi);
            return null;
        }
    }

    private static class InsertLikedCoordiTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.LikedCoordiDao mLikedCoordiDao;

        public InsertLikedCoordiTask(com.example.fashion.LikedCoordiDao likedCoordiDao) {
            mLikedCoordiDao = likedCoordiDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            com.example.fashion.LikedCoordi likedCoordi = new com.example.fashion.LikedCoordi();
            mLikedCoordiDao.setInsertLikedCoordi(likedCoordi);
            return null;
        }
    }

    private static class DeleteLikedCoordiTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.LikedCoordiDao mLikedCoordiDao;
        private com.example.fashion.LikedCoordi mLikedCoordi;

        public DeleteLikedCoordiTask(com.example.fashion.LikedCoordiDao likedCoordiDao, com.example.fashion.LikedCoordi likedCoordi) {
            mLikedCoordiDao = likedCoordiDao;
            mLikedCoordi = likedCoordi;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mLikedCoordiDao.setDeleteLikedCoordi(mLikedCoordi);
            return null;
        }
    }
    private static class UpdateLikedCoordiTask extends AsyncTask<Void, Void, Void> {
        private com.example.fashion.LikedCoordiDao mLikedCoordiDao;
        private com.example.fashion.LikedCoordi mLikedCoordi;

        public UpdateLikedCoordiTask(com.example.fashion.LikedCoordiDao likedCoordiDao, com.example.fashion.LikedCoordi likedCoordi) {
            mLikedCoordiDao = likedCoordiDao;
            mLikedCoordi = likedCoordi;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mLikedCoordiDao.setUpdateLikedCoordi(mLikedCoordi);
            return null;
        }
    }


}
