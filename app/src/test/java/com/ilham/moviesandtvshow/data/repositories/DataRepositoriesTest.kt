@file:Suppress("UNCHECKED_CAST")

package com.ilham.moviesandtvshow.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ilham.moviesandtvshow.PagedListUtil
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.source.FakeRepository
import com.ilham.moviesandtvshow.data.source.local.LocalRepository
import com.ilham.moviesandtvshow.data.source.network.RemoteRepository
import com.ilham.moviesandtvshow.utils.AppExecutor
import com.ilham.moviesandtvshow.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DataRepositoriesTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepository = mock(RemoteRepository::class.java)
    private val localRepository = mock(LocalRepository::class.java)
    private val appExecutor = mock(AppExecutor::class.java)
    private val repository = FakeRepository(localRepository, remoteRepository, appExecutor)

    private val listMovie = DummyData.generateDataMovieDummy()
    private val listTvShow = DummyData.generateDataTvShowDummy()
    private val movie = DummyData.generateDataMovieDummy()[0]
    private val tvShow = DummyData.generateDataTvShowDummy()[0]

    @Test
    fun getNowPlayingMovies() {
        val sourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        `when`(localRepository.getAllMovieAsPaged()).thenReturn(sourceFactory)
        repository.getNowPlayingMovies(1)

        val entity =
            Resource.Success(PagedListUtil.mockPagedList(DummyData.generateDataMovieDummy()))
        verify(localRepository).getAllMovieAsPaged()
        assertNotNull(entity.data)
        entity.data?.size?.let { assertEquals(listMovie.size, it) }
    }

    @Test
    fun getFavouriteMovies() {
        val sourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        `when`(localRepository.getFavouriteMovieAsPaged()).thenReturn(sourceFactory)
        repository.getFavouriteMovies()
        val entity =
            Resource.Success(PagedListUtil.mockPagedList(DummyData.generateDataMovieDummy()))
        assertNotNull(entity.data)
        entity.data?.size?.let { assertEquals(listMovie.size, it) }

    }

    @Test
    fun getPopularTV() {
        val sourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowModel>
        `when`(localRepository.getAllTvShowAsPaged()).thenReturn(sourceFactory)
        repository.getPopularTV(1)

        val entity =
            Resource.Success(PagedListUtil.mockPagedList(DummyData.generateDataTvShowDummy()))
        verify(localRepository).getAllTvShowAsPaged()
        assertNotNull(entity.data)
        entity.data?.size?.let { assertEquals(listTvShow.size, it) }
    }

    @Test
    fun getFavouriteTVShow() {
        val sourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowModel>
        `when`(localRepository.getFavouriteTvShowAsPaged()).thenReturn(sourceFactory)
        assertNotNull(repository.getFavouriteTVShow())
        val entity =
            Resource.Success(PagedListUtil.mockPagedList(DummyData.generateDataTvShowDummy()))
        assertNotNull(entity.data)
        entity.data?.size?.let { assertEquals(listTvShow.size, it) }
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieModel>()
        dummyMovie.value =DummyData.generateDataMovieDummy()[0]
        `when`(localRepository.getMovieDetail(550)).thenReturn(dummyMovie)
        assertNotNull(repository.getMovieDetail(550))
        val entity = localRepository.getMovieDetail(550)
        assertNotNull(entity)
        assertEquals(movie.id, entity.value?.id)
    }

    @Test
    fun getTvShowDetail() {
        val dummyMovie = MutableLiveData<TVShowModel>()
        dummyMovie.value =DummyData.generateDataTvShowDummy()[0]
        `when`(localRepository.getTvShowDetail(11)).thenReturn(dummyMovie)
        assertNotNull(repository.getTvShowDetail(11))
        val entity = localRepository.getTvShowDetail(11)
        assertNotNull(entity)
        assertEquals(tvShow.id, entity.value?.id)
    }

}