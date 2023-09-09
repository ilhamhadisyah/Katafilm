package com.example.katafilm.data.local

import androidx.room.*
import com.example.katafilm.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie order by popularity DESC")
    fun getAll(): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movie WHERE id =:id")
    fun getMovieById(id : Int) : Movie?

    @Query("UPDATE movie SET isFavourite = :isTrue WHERE id = :movieId")
    fun setFavourite(isTrue : Int, movieId : Int)

    @Delete
    fun delete(movie: Movie)

    @Delete
    fun deleteAll(movie: List<Movie>)
}