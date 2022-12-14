package com.example.sportsql;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MatchDao {

    @Query("SELECT * FROM match")
    List<Match> getAll();

    @Query("SELECT * FROM match WHERE id = :id")
    Match getById(long id);

    @Insert
    void insert(Match match);

    @Update
    void update(Match match);

    @Delete
    void delete(Match match);
}
