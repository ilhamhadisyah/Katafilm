package com.example.katafilm.data

import com.example.katafilm.data.local.MovieDao
import com.example.katafilm.data.local.ShowDao
import com.example.katafilm.data.remote.MovieRemoteDataSource
import com.example.katafilm.model.MovieDesc
import com.example.katafilm.model.Result
import com.example.katafilm.model.TrendingMovieResponse
import com.example.katafilm.model.TrendingShowResponse
import com.example.katafilm.model.TvShowDesc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository which fetches data from Remote or Local data sources
 */

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDao: MovieDao,
    private val showDao: ShowDao
) {

    fun setFavouriteMovie(isFavourite: Int, movieId: Int) {
        movieDao.setFavourite(isFavourite, movieId)
    }

    fun setFavouriteShow(isFavourite: Int, showId: Int) {
        showDao.setFavourite(isFavourite, showId)
    }

    fun fetchTrendingMovies(): Flow<Result<TrendingMovieResponse>?> {
        return flow {
            emit(Result.loading())
            val result = movieRemoteDataSource.fetchTrendingMovies()

            if (result.status == Result.Status.SUCCESS) {
                result.data?.results?.let { it ->
                    movieDao.deleteAll(it)
                    movieDao.insertAll(it)
                }
            }
            emit(fetchTrendingMoviesCached())
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchTrendingMoviesCached(): Result<TrendingMovieResponse>? =
        movieDao.getAll()?.let {
            Result.success(TrendingMovieResponse(it))
        }

    fun fetchMovie(id: Int): Flow<Result<MovieDesc>> {
        return flow {
            emit(Result.loading())
            val cache = fetchTrendingMoviesCached()?.data?.results?.filter { it.id == id }
            val result = movieRemoteDataSource.fetchMovie(id)
            emit(
                result.copy(
                    data = result.data?.copy(isFavourite = cache?.first()?.isFavourite!!)
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    fun fetchTrendingShows(): Flow<Result<TrendingShowResponse>?> {
        return flow {
            emit(Result.loading())
            val result = movieRemoteDataSource.fetchTrendingShows()

            //Cache to database if response is successful
            if (result.status == Result.Status.SUCCESS) {
                result.data?.results?.let { it ->
                    showDao.deleteAll(it)
                    showDao.insertAll(it)
                }
            }
            emit(fetchTrendingShowsCached())
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchTrendingShowsCached(): Result<TrendingShowResponse>? =
        showDao.getAll()?.let {
            Result.success(TrendingShowResponse(it))
        }

    fun fetchShows(id: Int): Flow<Result<TvShowDesc>> {
        return flow {
            emit(Result.loading())
            val cache = fetchTrendingShowsCached()?.data?.results?.filter { it.id == id }
            val result = movieRemoteDataSource.fetchShow(id)
            emit(
                result.copy(
                    data = result.data?.copy(isFavourite = cache?.first()?.isFavorite!!)
                )
            )
        }.flowOn(Dispatchers.IO)
    }

}