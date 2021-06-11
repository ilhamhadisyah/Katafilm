package com.ilham.moviesandtvshow.data.source.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.source.network.retrofit.ApiService
import com.ilham.moviesandtvshow.utils.EspressoIdlingRes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(private val service: ApiService) {

    fun getNowPlayingMoviesAsLiveData(page: Int): LiveData<ApiResponse<List<MovieModel>>> {
        EspressoIdlingRes.increment()
        val result = MutableLiveData<ApiResponse<List<MovieModel>>>()
        GlobalScope.launch {
            val postRequest = service.getNowPlayingMoviesAsync(page)
            val postResponse = postRequest.await().results
            result.postValue(ApiResponse.success(postResponse as List<MovieModel>))
            EspressoIdlingRes.decrement()
        }
        return result
    }

    fun getPopularTvShowAsLiveData(page: Int): LiveData<ApiResponse<List<TVShowModel>>> {
        EspressoIdlingRes.increment()
        val result = MutableLiveData<ApiResponse<List<TVShowModel>>>()
        GlobalScope.launch {
            val postRequest = service.getPopularTVAsync(page)
            val postResponse = postRequest.await().results
            result.postValue(ApiResponse.success(postResponse as List<TVShowModel>))
            EspressoIdlingRes.decrement()
        }
        return result
    }

    fun getMovieDetail(id : Int): LiveData<ApiResponse<MovieModel>>{
        EspressoIdlingRes.increment()
        val result = MutableLiveData<ApiResponse<MovieModel>>()
        GlobalScope.launch {
            val postRequest = service.movieDetailAsync(id)
            val postResponse = postRequest.await()
            result.postValue(ApiResponse.success(postResponse))
            EspressoIdlingRes.decrement()
        }
        return result
    }

    fun getTvShowDetail(id : Int) : LiveData<ApiResponse<TVShowModel>>{
        EspressoIdlingRes.increment()
        val result = MutableLiveData<ApiResponse<TVShowModel>>()
        GlobalScope.launch {
            val postRequest = service.tvShowDetailAsync(id)
            val postResponse = postRequest.await()
            result.postValue(ApiResponse.success(postResponse))
            EspressoIdlingRes.decrement()
        }
        return result
    }
}