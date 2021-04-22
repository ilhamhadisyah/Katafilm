package com.ilham.moviesandtvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilham.moviesandtvshow.data.MovieData
import com.ilham.moviesandtvshow.data.TVShowData
import com.ilham.moviesandtvshow.data.model.Movie
import com.ilham.moviesandtvshow.data.model.TVShow

class DetailViewModel : ViewModel() {
    private val movieDetail = MutableLiveData<Movie>()
    private val tvDetail = MutableLiveData<TVShow>()

    fun getMovieDataDetails(position: Int): LiveData<Movie> {
        movieDetail.postValue(MovieData.getMovieDetail(position))
        return movieDetail
    }

    fun getTVDataDetails(position: Int): LiveData<TVShow> {
        tvDetail.postValue(TVShowData.getTvDetail(position))
        return tvDetail
    }
}