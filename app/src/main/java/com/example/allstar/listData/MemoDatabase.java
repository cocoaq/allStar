package com.example.allstar.listData;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities = {Memo.class}, version = 1, exportSchema = false )
public abstract class MemoDatabase extends RoomDatabase {
    public abstract MemoDao memoDao();
}
