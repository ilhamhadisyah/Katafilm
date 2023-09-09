package com.example.katafilm.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katafilm.data.MovieRepository
import com.example.katafilm.model.TrendingMovieResponse
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.katafilm.model.Result
import com.example.katafilm.model.TrendingShowResponse
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movieList = MutableLiveData<Result<TrendingMovieResponse>>()
    val movieList = _movieList

    private val _tvShowList = MutableLiveData<Result<TrendingShowResponse>>()
    val tvShowList get() = _tvShowList


    fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.fetchTrendingMovies().collect {
                _movieList.value = it
            }
        }
    }

    fun fetchShows(){
        viewModelScope.launch {
            movieRepository.fetchTrendingShows().collect{
                _tvShowList.value = it
            }
        }
    }
}