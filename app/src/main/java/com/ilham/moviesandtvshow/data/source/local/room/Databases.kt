package com.ilham.moviesandtvshow.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel

@Database(entities = [MovieModel::class, TVShowModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class Databases : RoomDatabase() {
    abstract fun databaseObject(): Dao
}