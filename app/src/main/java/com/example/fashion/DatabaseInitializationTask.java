package com.example.fashion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import org.json.JSONException;


public class DatabaseInitializationTask extends AsyncTask<Void, Void, DataBase> {
    private final Context mContext;
    private final DatabaseInitializationListener mListener;

    public interface DatabaseInitializationListener {
        void onDatabaseInitialized(DataBase database) throws JSONException;
    }

    public DatabaseInitializationTask(Context context, DatabaseInitializationListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected DataBase doInBackground(Void... voids) {
        return Room.databaseBuilder(mContext,DataBase.class,"Fashion Genie_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    protected void onPostExecute(DataBase database) {
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
