package com.example.android.projectlattice.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.projectlattice.DataModel.UserInfo;

@Dao
public interface UserDao {



    @Insert
    void insertUserInfo(UserInfo userInfo);

    @Query("SELECT * FROM local_database WHERE id = :id")
    LiveData<UserInfo> loadTaskById(int id);


}
