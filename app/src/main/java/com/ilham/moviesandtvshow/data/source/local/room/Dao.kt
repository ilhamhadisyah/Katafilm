package com.ilham.moviesandtvshow.data.source.local.room


import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel

@Dao
interface Dao {

    @Query("SELECT * FROM movie")
    fun getMovie(): DataSource.Factory<Int, MovieModel>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavouriteMovies(): DataSource.Factory<Int, MovieModel>

    @Query("SELECT * FROM movie where id = :movieId")
    fun getMovieDetail(movieId : String) : LiveData<MovieModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(model: List<MovieModel>): LongArray

    @Update
    fun updateMovie(movieModel: MovieModel): Int

    @Query("SELECT * FROM tv_show")
    fun getTvShow(): DataSource.Factory<Int, TVShowModel>

    @Query("SELECT * FROM tv_show where isFavorite = 1")
    fun getFavouriteTvShow(): DataSource.Factory<Int, TVShowModel>

    @Query("SELECT * FROM tv_show where id = :tvId")
    fun getTvShowDetail(tvId : String) : LiveData<TVShowModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShow(model: List<TVShowModel>): LongArray

    @Update
    fun updateTvShow(tvShowModel: TVShowModel): Int

}