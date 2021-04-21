package com.ilham.moviesandtvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilham.moviesandtvshow.data.MovieData
import com.ilham.moviesandtvshow.data.model.Movie

class  HomeViewModel : ViewModel() {
    private val movieList = MutableLiveData<ArrayList<Movie>>()

    fun getMoviedata(): LiveData<ArrayList<Movie>> {
        movieList.postValue(MovieData.listMovie)
        return movieList
    }
}