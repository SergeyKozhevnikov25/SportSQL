package com.example.sportsql;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Match {

   @PrimaryKey(autoGenerate = true)
    public long id;

    public String team1;
    public String team2;
    public String score;
    public String date;
}
