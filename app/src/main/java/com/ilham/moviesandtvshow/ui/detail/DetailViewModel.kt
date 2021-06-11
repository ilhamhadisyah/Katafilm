package com.ilham.moviesandtvshow.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.repositories.DataRepositories
import com.ilham.moviesandtvshow.data.repositories.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor(private var movieRepositories: DataRepositories) :
    ViewModel() {
    fun getMovieDetail(id: Int): LiveData<Resource<MovieModel>> =
        movieRepositories.getMovieDetail(id)

    fun setFavMovie(movieModel: MovieModel, status: Boolean) {
        movieRepositories.setFavouriteMovie(movieModel, status)
    }
}