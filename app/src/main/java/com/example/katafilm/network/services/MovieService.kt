package com.example.katafilm.network.services

import com.example.katafilm.model.MovieDesc
import com.example.katafilm.model.TrendingMovieResponse
import com.example.katafilm.model.TrendingShowResponse
import com.example.katafilm.model.TvShowDesc
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit API Service
 */
interface MovieService {

    @GET("/3/trending/movie/week")
    suspend fun getPopularMovies() : Response<TrendingMovieResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int) : Response<MovieDesc>

    @GET("/3/trending/tv/week")
    suspend fun getPopularShows() : Response<TrendingShowResponse>

    @GET("/3/tv/{tv_id}")
    suspend fun getShow(@Path("tv_id") id: Int) : Response<TvShowDesc>
}