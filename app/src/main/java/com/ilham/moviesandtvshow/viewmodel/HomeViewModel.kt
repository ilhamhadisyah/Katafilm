package com.ilham.moviesandtvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilham.moviesandtvshow.data.MovieData
import com.ilham.moviesandtvshow.data.TVShowData
import com.ilham.moviesandtvshow.data.model.Movie
import com.ilham.moviesandtvshow.data.model.TVShow

class  HomeViewModel : ViewModel() {
    private val movieList = MutableLiveData<ArrayList<Movie>>()
    private val tvList = MutableLiveData<ArrayList<TVShow>>()

    fun getMovieData(): LiveData<ArrayList<Movie>> {
        movieList.postValue(MovieData.listMovie)
        return movieList
    }
    fun getTvData(): LiveData<ArrayList<TVShow>>{
        tvList.postValue(TVShowData.listTV)
        return tvList
    }
}