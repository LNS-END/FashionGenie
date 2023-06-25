package com.example.fashion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import org.json.JSONException;


public class DatabaseInitializationTask extends AsyncTask<Void, Void, com.example.fashion.DataBase> {
    private final Context mContext;
    private final DatabaseInitializationListener mListener;

    public interface DatabaseInitializationListener {
        void onDatabaseInitialized(com.example.fashion.DataBase database) throws JSONException;


    }

    public DatabaseInitializationTask(Context context, DatabaseInitializationListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected com.example.fashion.DataBase doInBackground(Void... voids) {
        return Room.databaseBuilder(mContext, com.example.fashion.DataBase.class,"Fashion Genie_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    protected void onPostExecute(com.example.fashion.DataBase database) {
        if (mListener != null) {
            try{
                mListener.onDatabaseInitialized(database);
            } catch (JSONException e){
                e.printStackTrace();
                Toast.makeText(mContext, "An error occurred while initializing the database.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
