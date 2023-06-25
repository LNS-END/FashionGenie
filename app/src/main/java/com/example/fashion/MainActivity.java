package com.example.fashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.File;
import android.os.AsyncTask;
import android.util.Log;
import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import android.os.AsyncTask;

import com.example.fashion.BagDao;
import com.example.fashion.CoordiDao;
import com.example.fashion.DatabaseInitializationTask;
import com.example.fashion.HeadWearDao;
import com.example.fashion.LikedCoordiDao;
import com.example.fashion.OuterDao;
import com.example.fashion.PantsDao;
import com.example.fashion.R;
import com.example.fashion.ShoesDao;
import com.example.fashion.TopDao;
import com.example.fashion.WeatherDao;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements DatabaseInitializationTask.DatabaseInitializationListener {
    private HeadWearDao mHeadWearDao;
    private BagDao mBagDao;
    private OuterDao mOuterDao;
    private PantsDao mPantsDao;
    private ShoesDao mShoesDao;
    private TopDao mTopDao;
    private CoordiDao mcoordiDao;
    private WeatherDao mWeatherDao;
    private LikedCoordiDao mLikedCoordiDao;

    private ImageView bagImageView;
    private ImageView headwearImageView;
    private ImageView outerImageView;
    private ImageView pantsImageView;
    private ImageView shoesImageView;
    private ImageView topImageView;

    private Button btn_Style_ton;
    private Button btn_Style_closet;

    private Button btn_Style_we;

    private static final String API_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
    private static final String SERVICE_KEY = "%2BS0YUmIviJtQT3mn%2F7AIROkt4IqYMhL7cLGNtL3ukN4XtBgUJRn4XLF27Vq315kgew358sxOzJBcUTKL3qAKFg%3D%3D";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseInitializationTask initializationTask = new DatabaseInitializationTask(this, this);
        initializationTask.execute();

        //bagImageView = findViewById(R.id.bag_image_view);
        //headwearImageView = findViewById(R.id.headwear_image_view);
        //outerImageView = findViewById(R.id.outer_image_view);
        //pantsImageView = findViewById(R.id.pants_image_view);
        //shoesImageView = findViewById(R.id.shoes_image_view);
        //topImageView = findViewById(R.id.top_image_view);

        btn_Style_closet = findViewById(R.id.MyCloset);
        btn_Style_closet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyclosetActivity.class);
                startActivity(intent); // 엑티비티 이동
            }
        });

        btn_Style_ton = findViewById(R.id.todayOf_style);
        btn_Style_ton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrieveWeatherInfo();
                Intent intent = new Intent(MainActivity.this,style_of_today.class);
                startActivity(intent); // 엑티비티 이동
            }
        });




        String bagImagePath = getFilePathFromInternalStorage("bag_image.jpg");
        if (bagImagePath != null) {
            displayImage(bagImagePath, bagImageView);
        }

        String headwearImagePath = getFilePathFromInternalStorage("headwear_image.jpg");
        if (headwearImagePath != null) {
            displayImage(headwearImagePath, headwearImageView);
        }

        String outerImagePath = getFilePathFromInternalStorage("outer_image.jpg");
        if (outerImagePath != null) {
            displayImage(outerImagePath, outerImageView);
        }

        String pantsImagePath = getFilePathFromInternalStorage("pants_image.jpg");
        if (pantsImagePath != null) {
            displayImage(pantsImagePath, pantsImageView);
        }

        String shoesImagePath = getFilePathFromInternalStorage("shoes_image.jpg");
        if (shoesImagePath != null) {
            displayImage(shoesImagePath, shoesImageView);
        }

        String topImagePath = getFilePathFromInternalStorage("top_image.jpg");
        if (topImagePath != null) {
            displayImage(topImagePath, topImageView);
        }
    }

    private void retrieveWeatherInfo() {
        new RetrieveWeatherTask().execute();
    }

    // 날씨 정보를 가져오는 비동기 태스크 클래스입니다.
    private class RetrieveWeatherTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                String nx = "58";  // 광주광역시의 nx 격자 좌표
                String ny = "74"; // 광주광역시의 ny 격자 좌표
                String requestUrl = API_URL + "?serviceKey=" + SERVICE_KEY + "&numOfRows=10&pageNo=1&base_date=20230625&base_time=1400&nx=" + nx + "&ny=" + ny;
                URL url = new URL(requestUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                reader.close();
                connection.disconnect();

                return builder.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }



        private HashMap<String, String> parseWeatherXml(String xmlData) {
            HashMap<String, String> weatherData = new HashMap<>();
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new StringReader(xmlData));
                int eventType = parser.getEventType();
                String category = "", obsrValue = "";

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("category")) {
                                eventType = parser.next();
                                if (eventType == XmlPullParser.TEXT) {
                                    category = parser.getText();
                                }
                            } else if (parser.getName().equals("obsrValue")) {
                                eventType = parser.next();
                                if (eventType == XmlPullParser.TEXT) {
                                    obsrValue = parser.getText();
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item")) {
                                weatherData.put(category, obsrValue);
                                category = "";
                                obsrValue = "";
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return weatherData;
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("WeatherInfo", result);
            if (result != null) {
                // Parse the XML
                HashMap<String, String> weatherData = parseWeatherXml(result);
                String temperature = weatherData.get("T1H");
                String precipitationType = weatherData.get("PTY");

                String weatherType;
                switch (precipitationType) {
                    case "1":
                        weatherType = "Rainy";
                        break;
                    case "2":
                        weatherType = "Snowy";
                        break;
                    default:
                        weatherType = "Sunny";
                        break;
                }

                Log.d("WeatherInfo", "Temperature: " + temperature + ", Weather: " + weatherType);

                WeatherData weatherDataObject = new WeatherData(temperature, weatherType);

                // Send data to server
                Service service = RetrofitClientInstance.getRetrofitInstance().create(Service.class);
                Call<ResponseBody> call = service.postWeatherData(weatherDataObject);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        // Handle response here
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        // Handle failure here
                    }
                });
            }

        }

    }

    private String getFilePathFromInternalStorage(String fileName) {
        File file = new File(getFilesDir(), fileName);
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    private void displayImage(String imagePath, ImageView imageView) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        }
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

//        InsertShoesTask insertShoesTask = new InsertShoesTask(mShoesDao);
//        insertShoesTask.execute();


//        Bag bag = new Bag();
//        bag.setImagePath("C:/Users/lmomj/Desktop/가방 이미지.png");
//
//        ImageView imageView = findViewById(R.id.my_image_view);
//        bag.loadImageIntoImageView(imageView);

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

//        Bag bagToUpdate = new Bag();
//        bagToUpdate.setID(1);
//        bagToUpdate.setImagePath("C:/Users/lmomj/Desktop/가방 이미지.png");

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
        private WeatherDao mWeatherDao;
        public InsertWeatherTask(WeatherDao weatherDao){
            mWeatherDao = weatherDao;
        }
        @Override
        protected  Void doInBackground(Void...voids){
            Weather weather = new Weather();
            mWeatherDao.setInsertWeather(weather);
            return null;
        }
    }
    private static class DeleteWeatherTask extends AsyncTask<Void, Void, Void> {
        private WeatherDao mWeatherDao;
        private Weather mWeather;

        public DeleteWeatherTask(WeatherDao weatherDao, Weather weather) {
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
        private WeatherDao mWeatherDao;
        private Weather mWeather;

        public UpdateWeatherTask(WeatherDao weatherDao,Weather weather) {
            mWeatherDao =weatherDao;
            mWeather =weather;
        }
        @Override
        protected Void doInBackground(Void... voids){
            mWeatherDao.setUpdateWeather(mWeather);
            return null;
        }
    }
    private static class SelectWeatherTask extends AsyncTask<Integer, Void, Weather> {
        private WeatherDao mWeatherDao;

        public SelectWeatherTask(WeatherDao weatherDao) {
            mWeatherDao = weatherDao;
        }

        @Override
        protected Weather doInBackground(Integer... ids) {
            return mWeatherDao.getWeatherById(ids[0]);
        }

        @Override
        protected void onPostExecute(Weather weather) {
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
        private PantsDao mPantsDao;
        public InsertPantsTask(PantsDao pantsDao) {
            mPantsDao = pantsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Pants pants = new Pants();
            pants.setSeason("Summer");
            pants.setBrand("Adidas");
            pants.setColor("Red");
            pants.setCategory("Gym");
            pants.setImagePath("");
            pants.setCloth("casual");
            pants.setTextile("soft");
            pants.setImagePath("NULL");
            mPantsDao.setInsertPants(pants);
            return null;
        }
    }
    private static class DeletePantsTask extends AsyncTask<Void, Void, Void> {
        private PantsDao mPantsDao;
        private Pants mPants;

        public DeletePantsTask(PantsDao pantsDao, Pants pants) {
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
        private PantsDao mPantsDao;
        private Pants mPants;

        public UpdatePantsTask(PantsDao pantsDao,Pants pants) {
            mPantsDao = pantsDao;
            mPants = pants;
        }
        @Override
        protected Void doInBackground(Void... voids){
            mPantsDao.setUpdatePants(mPants);
            return null;
        }
    }
    private static class SelectPantsTask extends AsyncTask<Integer, Void, Pants> {
        private ImageView mImageView;
        private Context mContext;
        private PantsDao mPantsDao;


        public SelectPantsTask(PantsDao pantsDao) {
            mPantsDao = pantsDao;
//            mContext = context;
//            mImageView = imageView;
        }

        @Override
        protected Pants doInBackground(Integer... ids) {
            return mPantsDao.getPantsById(ids[0]);
        }

        @Override
        protected void onPostExecute(Pants pants) {
            super.onPostExecute(pants);
            if (pants != null) {
                String pantsBrand = pants.getBrand();
                String pantsCategory = pants.getCategory();
                String pantsColor = pants.getColor();
                String pantsTextile = pants.getTextile();
                String pantsSeason = pants.getSeason();
                String pantsStyle = pants.getCloth();
                String pantsImagePath = pants.getImagePath();

                Log.d("SelectPantsTask", "Selected Pants Category: " + pantsCategory);
                Log.d("SelectPantsTask", "Selected Pants Brand: " + pantsBrand);
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
        private TopDao mTopDao;
        private Top mTop;

        public DeleteTopTask(TopDao topDao, Top top) {
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
        private TopDao mTopDao;

        public InsertTopTask(TopDao topDao) {
            mTopDao = topDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Top top = new Top();
            top.setSeason("Summer");
            top.setBrand("Adidas");
            top.setColor("Red");
            top.setCategory("Gym");
            top.setImagePath("");
            top.setCloth("casual");
            top.setTextile("soft");
            top.setImagePath("NULL");
            mTopDao.setInsertTop(top);
            return null;
        }
    }
    private static class UpdateTopTask extends AsyncTask<Void, Void, Void> {
        private TopDao mTopDao;
        private Top mTop;

        public UpdateTopTask(TopDao topDao, Top top) {
            mTopDao = topDao;
            mTop = top;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mTopDao.setUpdateTop(mTop);
            return null;
        }
    }
    private static class SelectTopTask extends AsyncTask<Integer, Void, Top> {
        private TopDao mTopDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectTopTask(TopDao topDao) {
            mTopDao = topDao;
//            mImageView = imageView;
//            mContext = context;
        }

        @Override
        protected Top doInBackground(Integer... ids) {
            return mTopDao.getTopById(ids[0]);
        }

        @Override
        protected void onPostExecute(Top top) {
            super.onPostExecute(top);
            if (top != null) {
                String topBrand = top.getBrand();
                String topCategory = top.getCategory();
                String topColor = top.getColor();
                String topTextile = top.getTextile();
                String topSeason = top.getSeason();
                String topStyle = top.getCloth();
                String TopImagePath = top.getImagePath();

                Log.d("SelectTopTask", "Selected Top Category: " + topCategory);
                Log.d("SelectTopTask", "Selected Top Brand: " + topBrand);
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
    private static class InsertOuterTask extends AsyncTask<Outer, Void, Void> {
        private OuterDao mOuterDao;

        public InsertOuterTask(OuterDao outerDao) {
            mOuterDao = outerDao;
        }

        @Override
        protected Void doInBackground(Outer... outers) {
            Outer outer = new Outer();
            outer.setSeason("Summer");
            outer.setBrand("Adidas");
            outer.setColor("Red");
            outer.setCategory("Gym");
            outer.setImagePath("");
            outer.setCloth("casual");
            outer.setTextile("soft");
            outer.setImagePath("NULL");
            mOuterDao.setInsertOuter(outer);
            return null;
        }
    }
    private static class DeleteOuterTask extends AsyncTask<Outer, Void, Void> {
        private OuterDao mOuterDao;
        private Outer mOuter;

        public DeleteOuterTask(OuterDao outerDao, Outer outer) {
            mOuterDao = outerDao;
            mOuter = outer;
        }

        @Override
        protected Void doInBackground(Outer... outers) {
            mOuterDao.setDeleteOuter(mOuter);
            return null;
        }
    }
    private static class UpdateOuterTask extends AsyncTask<Outer, Void, Void> {
        private OuterDao mOuterDao;
        private Outer mOuter;

        public UpdateOuterTask(OuterDao outerDao, Outer outer) {
            mOuterDao = outerDao;
            mOuter = outer;
        }

        @Override
        protected Void doInBackground(Outer... outer) {
            mOuterDao.setUpdateOuter(mOuter);
            return null;
        }
    }
    private static class SelectOuterTask extends AsyncTask<Integer, Void, Outer> {
        private OuterDao mOuterDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectOuterTask(OuterDao outerDao) {
            mOuterDao = outerDao;
//            mContext = context;
//            mImageView = imageView;
        }

        @Override
        protected Outer doInBackground(Integer... ids) {
            return mOuterDao.getOuterById(ids[0]);
        }

        @Override
        protected void onPostExecute(Outer outer) {
            super.onPostExecute(outer);
            if (outer != null) {
                String outerBrand = outer.getBrand();
                String outerCategory = outer.getCategory();
                String outerColor = outer.getColor();
                String outerTextile = outer.getTextile();
                String outerSeason = outer.getSeason();
                String outerStyle = outer.getCloth();
                String outerImagePath = outer.getImagePath();

                Log.d("SelectOuterTask", "Selected Outer Category: " + outerCategory);
                Log.d("SelectOuterTask", "Selected Outer Brand: " + outerBrand);
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
    private static class InsertShoesTask extends AsyncTask<Shoes, Void, Void> {
        private ShoesDao mShoesDao;

        public InsertShoesTask(ShoesDao shoesDao) {
            mShoesDao = shoesDao;
        }

        @Override
        protected Void doInBackground(Shoes... shoeses) {
            Shoes shoes = new Shoes();
            shoes.setSeason("fuck");
            shoes.setBrand("SSibal");
            shoes.setColor("asshole");
            shoes.setCategory("Gym");
            shoes.setImagePath("");
            shoes.setCloth("casual");
            shoes.setTextile("soft");
            shoes.setImagePath("NULL");
            mShoesDao.setInsertShoes(shoes);
            return null;
        }
    }
    private static class DeleteShoesTask extends AsyncTask<Shoes, Void, Void> {
        private ShoesDao mShoesDao;
        private Shoes mShoes;

        public DeleteShoesTask(ShoesDao shoesDao, Shoes shoes) {
            mShoesDao =shoesDao;
            mShoes = shoes;
        }

        @Override
        protected Void doInBackground(Shoes... shoes) {
            mShoesDao.setDeleteShoes(mShoes);
            return null;
        }
    }
    private static class UpdateShoesTask extends AsyncTask<Shoes, Void, Void> {
        private ShoesDao mShoesDao;
        private Shoes mShoes;

        public UpdateShoesTask(ShoesDao shoesDao, Shoes shoes) {
            mShoesDao =shoesDao;
            mShoes = shoes;
        }

        @Override
        protected Void doInBackground(Shoes... shoes) {
            mShoesDao.setUpdateShoes(mShoes);
            return null;
        }
    }
    private static class SelectShoesTask extends AsyncTask<Integer, Void, Shoes> {
        private ShoesDao mShoesDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectShoesTask(ShoesDao shoesDao) {
            mShoesDao = shoesDao;
//            mContext = context;
//            mImageView = imageView;
        }

        @Override
        protected Shoes doInBackground(Integer... ids) {
            return mShoesDao.getShoesById(ids[0]);
        }

        @Override
        protected void onPostExecute(Shoes shoes) {
            super.onPostExecute(shoes);
            if (shoes != null) {
                String shoesBrand = shoes.getBrand();
                String shoesCategory = shoes.getCategory();
                String shoesColor = shoes.getColor();
                String shoesTextile = shoes.getTextile();
                String shoesSeason = shoes.getSeason();
                String shoesStyle = shoes.getCloth();
                String shoesImagePath = shoes.getImagePath();

                Log.d("SelectShoesTask", "Selected Shoes Category: " + shoesCategory);
                Log.d("SelectShoesTask", "Selected Shoes Brand: " + shoesBrand);
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
    private static class InsertBagTask extends AsyncTask<Bag, Void, Void> {
        private BagDao mBagDao;

        public InsertBagTask(BagDao bagDao) {
            mBagDao = bagDao;
        }

        @Override
        protected Void doInBackground(Bag... bags) {
            Bag bag = new Bag();
            bag.setSeason("Summer");
            bag.setBrand("Adidas");
            bag.setColor("Red");
            bag.setCategory("Gym");
            bag.setImagePath("");
            bag.setCloth("casual");
            bag.setTextile("soft");
            bag.setImagePath("NULL");
            mBagDao.setInsertBag(bag);
            return null;
        }
    }
    private static class DeleteBagTask extends AsyncTask<Bag, Void, Void> {
        private BagDao mBagDao;
        private Bag mBag;

        public DeleteBagTask(BagDao bagDao, Bag bag) {
            mBagDao = bagDao;
            mBag = bag;
        }

        @Override
        protected Void doInBackground(Bag... bags) {
            mBagDao.setDeleteBag(mBag);
            return null;
        }
    }
    private static class SelectBagTask extends AsyncTask<Integer, Void, Bag> {
        private BagDao mBagDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectBagTask(BagDao bagDao) {
            mBagDao = bagDao;
//            mContext = context;
//            mImageView = imageView;

        }

        @Override
        protected Bag doInBackground(Integer... ids) {

            return mBagDao.getBagById(ids[0]);
        }

        @Override
        protected void onPostExecute(Bag bag) {
            super.onPostExecute(bag);
            if (bag != null) {
                String bagBrand = bag.getBrand();
                String bagCategory = bag.getCategory();
                String bagColor = bag.getColor();
                String bagTextile = bag.getTextile();
                String bagSeason = bag.getSeason();
                String bagStyle = bag.getCloth();
                String bagImagePath = bag.getImagePath();

                Log.d("SelectBagTask", "Selected Bag Category: " + bagCategory);
                Log.d("SelectBagTask", "Selected Bag Brand: " + bagBrand);
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
    private static class UpdateBagTask extends AsyncTask<Bag, Void, Void> {
        private BagDao mBagDao;
        private Bag mBag;

        public UpdateBagTask(BagDao bagDao, Bag bag) {
            mBagDao = bagDao;
            mBag = bag;
        }

        @Override
        protected Void doInBackground(Bag... bags) {
            mBagDao.setUpdateBag(mBag);
            return null;
        }
    }


    private static class InsertHeadWearTask extends AsyncTask<Void, Void, Void> {
        private HeadWearDao mHeadWearDao;

        public InsertHeadWearTask(HeadWearDao headWearDao) {
            mHeadWearDao = headWearDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HeadWear headWear = new HeadWear();
            headWear.setSeason("Summer");
            headWear.setBrand("Adidas");
            headWear.setColor("Red");
            headWear.setCategory("Gym");
            headWear.setImagePath("");
            headWear.setCloth("casual");
            headWear.setTextile("soft");
            headWear.setImagePath("NULL");
            mHeadWearDao.setInsertHeadWear(headWear);
            return null;
        }
    }
    private static class DeleteHeadWearTask extends AsyncTask<Void, Void, Void> {
        private HeadWearDao mHeadWearDao;
        private HeadWear mHeadWear;

        public DeleteHeadWearTask(HeadWearDao headWearDao, HeadWear headWear) {
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
        private HeadWearDao mHeadWearDao;
        private HeadWear mHeadWear;

        public UpdateHeadWearTask(HeadWearDao headWearDao, HeadWear headWear) {
            mHeadWearDao = headWearDao;
            mHeadWear = headWear;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mHeadWearDao.setUpdateHeadWear(mHeadWear);
            return null;
        }
    }
    private static class SelectHeadWearTask extends AsyncTask<Integer, Void, HeadWear> {
        private HeadWearDao mHeadWearDao;
        private ImageView mImageView;
        private Context mContext;

        public SelectHeadWearTask(HeadWearDao headWearDao) {
            mHeadWearDao = headWearDao;
        }

        @Override
        protected HeadWear doInBackground(Integer... ids) {
            return mHeadWearDao.getHeadWearById(ids[0]);
        }

        @Override
        protected void onPostExecute(HeadWear headWear) {
            super.onPostExecute(headWear);
            if (headWear != null) {
                String headWearBrand = headWear.getBrand();
                String headWearCategory = headWear.getCategory();
                String headWearColor = headWear.getColor();
                String headWearTextile = headWear.getTextile();
                String headWearSeason = headWear.getSeason();
                String headWearStyle = headWear.getCloth();
                String headWearImagePath = headWear.getImagePath();

                Log.d("SelectHeadWearTask", "Selected HeadWear Category: " + headWearCategory);
                Log.d("SelectHeadWearTask", "Selected HeadWear Brand: " + headWearBrand);
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
        private CoordiDao mCoordiDao;

        public InsertCoordiTask(CoordiDao coordiDao) {
            mCoordiDao = coordiDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Coordi coordi = new Coordi();
            mCoordiDao.setInsertCoordi(coordi);
            return null;
        }
    }
    private static class DeleteCoordiTask extends AsyncTask<Void, Void, Void> {
        private CoordiDao mCoordiDao;
        private Coordi mCoordi;

        public DeleteCoordiTask(CoordiDao coordiDao, Coordi coordi) {
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
        private CoordiDao mCoordiDao;
        private Coordi mCoordi;

        public UpdateCoordiTask(CoordiDao coordiDao, Coordi coordi) {
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
        private LikedCoordiDao mLikedCoordiDao;

        public InsertLikedCoordiTask(LikedCoordiDao likedCoordiDao) {
            mLikedCoordiDao = likedCoordiDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            LikedCoordi likedCoordi = new LikedCoordi();
            mLikedCoordiDao.setInsertLikedCoordi(likedCoordi);
            return null;
        }
    }

    private static class DeleteLikedCoordiTask extends AsyncTask<Void, Void, Void> {
        private LikedCoordiDao mLikedCoordiDao;
        private LikedCoordi mLikedCoordi;

        public DeleteLikedCoordiTask(LikedCoordiDao likedCoordiDao, LikedCoordi likedCoordi) {
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
        private LikedCoordiDao mLikedCoordiDao;
        private LikedCoordi mLikedCoordi;

        public UpdateLikedCoordiTask(LikedCoordiDao likedCoordiDao, LikedCoordi likedCoordi) {
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
