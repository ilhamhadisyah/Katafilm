package com.ilham.moviesandtvshow.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel

interface DataSourceRepository {
    fun getNowPlayingMovies(page: Int): LiveData<Resource<PagedList<MovieModel>>>
    fun getFavouriteMovies(): LiveData<Resource<PagedList<MovieModel>>>
    fun setFavouriteMovie(model: MovieModel, state: Boolean)
    fun getMovieDetail(id: Int) : LiveData<Resource<MovieModel>>

    fun getPopularTV(page: Int): LiveData<Resource<PagedList<TVShowModel>>>
    fun getFavouriteTVShow(): LiveData<Resource<PagedList<TVShowModel>>>
    fun setFavouriteTVShow(model: TVShowModel, state: Boolean)
    fun getTvShowDetail(id: Int) : LiveData<Resource<TVShowModel>>
}