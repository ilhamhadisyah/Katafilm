package com.example.katafilm.data.remote

import com.example.katafilm.model.MovieDesc
import com.example.katafilm.model.Result
import com.example.katafilm.model.TrendingMovieResponse
import com.example.katafilm.model.TrendingShowResponse
import com.example.katafilm.model.TvShowDesc
import com.example.katafilm.network.services.MovieService
import com.example.katafilm.utils.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * fetches data from remote source
 */
class MovieRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchTrendingMovies(): Result<TrendingMovieResponse> {
        val movieService = retrofit.create(MovieService::class.java);
        return getResponse(
                request = { movieService.getPopularMovies() },
                defaultErrorMessage = "Error fetching Movie list")

    }

    suspend fun fetchMovie(id: Int): Result<MovieDesc> {
        val movieService = retrofit.create(MovieService::class.java);
        return getResponse(
                request = { movieService.getMovie(id) },
                defaultErrorMessage = "Error fetching Movie Description")
    }

    suspend fun fetchTrendingShows(): Result<TrendingShowResponse> {
        val movieService = retrofit.create(MovieService::class.java);
        return getResponse(
            request = { movieService.getPopularShows() },
            defaultErrorMessage = "Error fetching Movie list")

    }

    suspend fun fetchShow(id: Int): Result<TvShowDesc> {
        val movieService = retrofit.create(MovieService::class.java);
        return getResponse(
            request = { movieService.getShow(id) },
            defaultErrorMessage = "Error fetching Movie Description")
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }
}