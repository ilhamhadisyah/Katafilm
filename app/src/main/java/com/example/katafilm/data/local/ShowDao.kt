package com.example.katafilm.data.local

import androidx.room.*
import com.example.katafilm.model.Movie
import com.example.katafilm.model.TvShow

@Dao
interface ShowDao {

    @Query("SELECT * FROM tv_show order by popularity DESC")
    fun getAll(): List<TvShow>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(tvShows: List<TvShow>)

    @Query("SELECT * FROM tv_show WHERE id =:id")
    fun getShowById(id : Int) : TvShow?

    @Query("UPDATE tv_show SET isFavorite = :isTrue WHERE id = :tvShowId")
    fun setFavourite(isTrue : Int, tvShowId : Int)

    @Delete
    fun delete(tvShow: TvShow)

    @Delete
    fun deleteAll(tvShow: List<TvShow>)
}