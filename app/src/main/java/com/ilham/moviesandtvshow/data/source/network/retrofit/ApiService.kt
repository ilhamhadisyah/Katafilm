package com.ilham.moviesandtvshow.data.source.network.retrofit


import com.ilham.moviesandtvshow.BuildConfig
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.MovieResponse
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.models.TvShowResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface   ApiService {
    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}")
    fun getNowPlayingMoviesAsync(
        @Query("page") page: Int
    ): Deferred<MovieResponse>

    @GET("tv/popular?api_key=${BuildConfig.API_KEY}")
    fun getPopularTVAsync(
        @Query("page") page: Int
    ): Deferred<TvShowResponse>

    @GET("movie/{id}?api_key=${BuildConfig.API_KEY}")
    fun movieDetailAsync(@Path("id") id: Int): Deferred<MovieModel>

    @GET("tv/{id}?api_key=${BuildConfig.API_KEY}")
    fun tvShowDetailAsync(@Path("id") id: Int): Deferred<TVShowModel>


}