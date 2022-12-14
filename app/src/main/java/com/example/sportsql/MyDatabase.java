package com.example.sportsql;


import android.content.Context;

import androidx.room.Room;

public class MyDatabase {

    private static MatchDatabase matchDatabase = null;

    public static MatchDatabase getDatabase(Context context) {

        if (matchDatabase == null) {
            matchDatabase =  Room.databaseBuilder(context,
                    MatchDatabase.class, "database").allowMainThreadQueries().build();
        }
        return matchDatabase;
    }
}
