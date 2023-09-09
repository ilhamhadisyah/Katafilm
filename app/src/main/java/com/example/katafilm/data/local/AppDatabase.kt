package com.example.katafilm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.katafilm.data.GenreConverters
import com.example.katafilm.model.Movie
import com.example.katafilm.model.TvShow

@Database(entities = [Movie::class, TvShow::class], version = 2, exportSchema = true)
@TypeConverters(GenreConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun showDao() : ShowDao
}