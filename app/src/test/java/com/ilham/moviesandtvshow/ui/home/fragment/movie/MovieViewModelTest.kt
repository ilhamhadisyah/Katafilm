package com.ilham.moviesandtvshow.ui.home.fragment.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.repositories.DataRepositories
import com.ilham.moviesandtvshow.data.repositories.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepositories: DataRepositories

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<MovieModel>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieModel>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(dataRepositories)
    }

    @Test
    fun getDataMovie() {
        val dummyData = Resource.Success(moviePagedList)
        `when`(dummyData.data?.size).thenReturn(20)
        val movie = MutableLiveData<Resource<PagedList<MovieModel>>>()
        movie.value = dummyData

        `when`(dataRepositories.getNowPlayingMovies(1)).thenReturn(movie)
        val entity = movieViewModel.getDataMovie(1).value?.data
        verify(dataRepositories).getNowPlayingMovies(1)
        assertNotNull(entity)
        assertEquals(20, entity?.size)

        movieViewModel.getDataMovie(1).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyData)
    }
}