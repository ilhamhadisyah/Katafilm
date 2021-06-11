package com.ilham.moviesandtvshow.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.repositories.DataRepositories
import com.ilham.moviesandtvshow.data.repositories.Resource
import com.ilham.moviesandtvshow.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepositories: DataRepositories

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieModel>>


    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(dataRepositories)
    }

    @Test
    fun getMovieDetail() {
        val expectData = MutableLiveData<Resource<MovieModel>>()
        expectData.value = Resource.Success(DummyData.generateDataMovieDummy()[0])
        `when`(detailViewModel.getMovieDetail(1)).thenReturn(expectData)
        detailViewModel.getMovieDetail(1).observeForever(movieObserver)

        verify(movieObserver).onChanged(expectData.value)

        val expectValue  = expectData.value
        val actualValue = detailViewModel.getMovieDetail(1).value
        assertEquals(expectValue,actualValue)
    }
}


