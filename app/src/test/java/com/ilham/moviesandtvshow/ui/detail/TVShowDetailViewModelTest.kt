package com.ilham.moviesandtvshow.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ilham.moviesandtvshow.data.models.TVShowModel
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepositories: DataRepositories

    @Mock
    private lateinit var movieObserver: Observer<Resource<TVShowModel>>


    private lateinit var tvShowViewModel: TVShowDetailViewModel

    @Before
    fun setUp() {
        tvShowViewModel = TVShowDetailViewModel(dataRepositories)
    }

    @Test
    fun getTvShowDetail() {
        val expectData = MutableLiveData<Resource<TVShowModel>>()
        expectData.value = Resource.Success(DummyData.generateDataTvShowDummy()[0])
        Mockito.`when`(tvShowViewModel.getTvShowDetail(1)).thenReturn(expectData)
        tvShowViewModel.getTvShowDetail(1).observeForever(movieObserver)

        verify(movieObserver).onChanged(expectData.value)

        val expectValue  = expectData.value
        val actualValue = tvShowViewModel.getTvShowDetail(1).value
        assertEquals(expectValue,actualValue)
    }
}