package com.ilham.moviesandtvshow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ilham.moviesandtvshow.data.MovieData
import com.ilham.moviesandtvshow.data.TVShowData
import com.ilham.moviesandtvshow.data.model.Movie
import com.ilham.moviesandtvshow.data.model.TVShow
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.collections.ArrayList

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var movieViewModel: HomeViewModel
    private lateinit var tvViewModel: HomeViewModel
    @Mock lateinit var movieDataObserver : Observer<ArrayList<Movie>>
    @Mock lateinit var tvDataObserver : Observer<ArrayList<TVShow>>

    @Before
    fun setUp(){
        movieViewModel = HomeViewModel()
        tvViewModel = HomeViewModel()
        movieViewModel.getMovieData().observeForever(movieDataObserver)
        tvViewModel.getTvData().observeForever(tvDataObserver)
    }
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun getMovieData() {
        assertEquals(19,movieViewModel.getMovieData().value?.size)
        assertNotNull(movieViewModel.getMovieData())
        assertEquals(MovieData.listMovie,movieViewModel.getMovieData().value)
        assertEquals(MovieData.getMovieDetail(1), movieViewModel.getMovieData().value?.get(1))

    }

    @Test
    fun getTvData() {
        assertEquals(19,tvViewModel.getTvData().value?.size)
        assertNotNull(tvViewModel.getTvData())
        assertEquals(TVShowData.listTV,tvViewModel.getTvData().value)
        assertEquals(TVShowData.getTvDetail(0), tvViewModel.getTvData().value?.get(0))
    }
}