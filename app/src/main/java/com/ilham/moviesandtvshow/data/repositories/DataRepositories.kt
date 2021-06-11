@file:Suppress("UNREACHABLE_CODE")

package com.ilham.moviesandtvshow.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.source.local.LocalRepository
import com.ilham.moviesandtvshow.data.source.network.ApiResponse
import com.ilham.moviesandtvshow.data.source.network.RemoteRepository
import com.ilham.moviesandtvshow.utils.AppExecutor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositories @Inject constructor(
    private var localRepository: LocalRepository,
    private var remoteRepository: RemoteRepository,
    private var appExecutor: AppExecutor
) : DataSourceRepository {

    override fun getNowPlayingMovies(page: Int): LiveData<Resource<PagedList<MovieModel>>> {

        return object : NetworkBoundResource<PagedList<MovieModel>, List<MovieModel>>(appExecutor) {
            override fun loadFromDB(): LiveData<PagedList<MovieModel>> {

                return LivePagedListBuilder(localRepository.getAllMovieAsPaged(), 20).build()
            }

            override fun shouldFetch(data: PagedList<MovieModel>?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<MovieModel>>>? {

                return remoteRepository.getNowPlayingMoviesAsLiveData(page)
            }

            override fun saveCallResult(data: List<MovieModel>) {

                localRepository.insertMovie(data)
            }

        }.asLiveData()

    }

    override fun getFavouriteMovies(): LiveData<Resource<PagedList<MovieModel>>> {

        return object : NetworkBoundResource<PagedList<MovieModel>, List<MovieModel>>(appExecutor) {
            override fun loadFromDB(): LiveData<PagedList<MovieModel>> {

                return LivePagedListBuilder(localRepository.getFavouriteMovieAsPaged(), 20).build()
            }

            override fun shouldFetch(data: PagedList<MovieModel>?): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<MovieModel>>>? = null

            override fun saveCallResult(data: List<MovieModel>) {}

        }.asLiveData()

    }

    override fun setFavouriteMovie(model: MovieModel, state: Boolean) {
        val runnable = { localRepository.setFavouriteMovie(model, state) }
        appExecutor.diskIO().execute(runnable)
    }

    override fun getMovieDetail(id: Int): LiveData<Resource<MovieModel>> {
        return object : NetworkBoundResource<MovieModel,MovieModel>(appExecutor){
            override fun loadFromDB(): LiveData<MovieModel> {
                return localRepository.getMovieDetail(id)
            }

            override fun shouldFetch(data: MovieModel?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<MovieModel>>? {
                return remoteRepository.getMovieDetail(id)
            }

            override fun saveCallResult(data: MovieModel) {

            }

        }.asLiveData()
    }

    override fun getPopularTV(page: Int): LiveData<Resource<PagedList<TVShowModel>>> {

        return object :
            NetworkBoundResource<PagedList<TVShowModel>, List<TVShowModel>>(appExecutor) {
            override fun loadFromDB(): LiveData<PagedList<TVShowModel>> {

                return LivePagedListBuilder(localRepository.getAllTvShowAsPaged(), 20).build()
            }

            override fun shouldFetch(data: PagedList<TVShowModel>?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<TVShowModel>>>? {

                return remoteRepository.getPopularTvShowAsLiveData(page)
            }

            override fun saveCallResult(data: List<TVShowModel>) {

                localRepository.insertTvShow(data)
            }

        }.asLiveData()

    }

    override fun getFavouriteTVShow(): LiveData<Resource<PagedList<TVShowModel>>> {

        return object :
            NetworkBoundResource<PagedList<TVShowModel>, List<TVShowModel>>(appExecutor) {
            override fun loadFromDB(): LiveData<PagedList<TVShowModel>> {

                return LivePagedListBuilder(localRepository.getFavouriteTvShowAsPaged(), 20).build()
            }

            override fun shouldFetch(data: PagedList<TVShowModel>?): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<TVShowModel>>>? = null

            override fun saveCallResult(data: List<TVShowModel>) {}

        }.asLiveData()

    }

    override fun setFavouriteTVShow(model: TVShowModel, state: Boolean) {

        val runnable = {
            localRepository.setFavouriteTvShow(model, state)
        }
        appExecutor.diskIO().execute(runnable)

    }

    override fun getTvShowDetail(id: Int): LiveData<Resource<TVShowModel>> {
        return object : NetworkBoundResource<TVShowModel,TVShowModel>(appExecutor){
            override fun loadFromDB(): LiveData<TVShowModel> {
                return localRepository.getTvShowDetail(id)
            }

            override fun shouldFetch(data: TVShowModel?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<TVShowModel>>? {
                return remoteRepository.getTvShowDetail(id)
            }

            override fun saveCallResult(data: TVShowModel) {

            }

        }.asLiveData()
    }
}