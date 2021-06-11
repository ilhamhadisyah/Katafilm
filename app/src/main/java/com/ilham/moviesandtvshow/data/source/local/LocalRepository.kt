package com.ilham.moviesandtvshow.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.source.local.room.Dao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(private val movieDao: Dao) {
    fun getAllMovieAsPaged(): DataSource.Factory<Int, MovieModel> = movieDao.getMovie()

    fun getFavouriteMovieAsPaged(): DataSource.Factory<Int, MovieModel> =
        movieDao.getFavouriteMovies()

    fun insertMovie(movie: List<MovieModel>) {
        movieDao.insertMovie(movie)
    }

    fun setFavouriteMovie(movieModel: MovieModel, state: Boolean) {
        movieModel.isFavorite = state
        movieDao.updateMovie(movieModel)
    }

    fun getMovieDetail(id: Int): LiveData<MovieModel> =
        movieDao.getMovieDetail(id.toString())


    fun getAllTvShowAsPaged(): DataSource.Factory<Int, TVShowModel> = movieDao.getTvShow()

    fun getFavouriteTvShowAsPaged(): DataSource.Factory<Int, TVShowModel> =
        movieDao.getFavouriteTvShow()

    fun insertTvShow(tvShow: List<TVShowModel>) {
        movieDao.insertTvShow(tvShow)
    }

    fun setFavouriteTvShow(tvShowModel: TVShowModel, state: Boolean) {
        tvShowModel.isFavorite = state
        movieDao.updateTvShow(tvShowModel)
    }

    fun getTvShowDetail(id: Int): LiveData<TVShowModel> =
        movieDao.getTvShowDetail(id.toString())
}