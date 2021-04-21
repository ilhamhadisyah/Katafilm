package com.ilham.moviesandtvshow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ilham.moviesandtvshow.data.MovieData
import com.ilham.moviesandtvshow.data.TVShowData
import com.ilham.moviesandtvshow.data.model.Movie
import com.ilham.moviesandtvshow.data.model.TVShow
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class DetailViewModelTest {
    private lateinit var movieViewModel: DetailViewModel
    private lateinit var tvViewModel: DetailViewModel
    @Mock
    lateinit var movieDataObserver : Observer<Movie>
    @Mock
    lateinit var tvDataObserver : Observer<TVShow>

    @Before
    fun setUp(){
        movieViewModel = DetailViewModel()
        tvViewModel = DetailViewModel()
        movieViewModel.getMovieDataDetails(1).observeForever(movieDataObserver)
        tvViewModel.getTVDataDetails(1).observeForever(tvDataObserver)
    }
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun getMovieDataDetails() {
        val getFirstIndexData = MovieData.getMovieDetail(0)
        assertEquals(getFirstIndexData,movieViewModel.getMovieDataDetails(0).value)
        val getLastIndexData = MovieData.getMovieDetail(MovieData.listMovie.size-1)
        assertEquals(getLastIndexData,movieViewModel.getMovieDataDetails(MovieData.listMovie.size-1).value)
    }

    @Test
    fun getTVDataDetails() {
        val getFirstIndexData = TVShowData.getTvDetail(0)
        assertEquals(getFirstIndexData,tvViewModel.getTVDataDetails(0).value)
        val getLastIndexData = TVShowData.getTvDetail(TVShowData.listTV.size-1)
        assertEquals(getLastIndexData,tvViewModel.getTVDataDetails(TVShowData.listTV.size-1).value)
    }
}