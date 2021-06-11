package com.ilham.moviesandtvshow.ui.home.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.repositories.DataRepositories
import com.ilham.moviesandtvshow.data.repositories.Resource
import javax.inject.Inject

class MovieViewModel @Inject constructor(private var movieRepository: DataRepositories) :
    ViewModel() {
    fun getDataMovie(page: Int): LiveData<Resource<PagedList<MovieModel>>> =
        movieRepository.getNowPlayingMovies(page)
}