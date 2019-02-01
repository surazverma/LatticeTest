package com.example.android.projectlattice.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.example.android.projectlattice.DataModel.UserInfo;

@Database(entities = {UserInfo.class}, version = 1, exportSchema = false )

public abstract class UserDatabase extends RoomDatabase {
    private static final String LOG_TAG = UserDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "user_information";
    private static UserDatabase sInstance;

    public static UserDatabase getsInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new Database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        UserDatabase.class,
                        UserDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG,"Getting the database instance");
        return sInstance;
    }

    public abstract UserDao userDao();
}
