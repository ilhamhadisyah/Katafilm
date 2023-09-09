//package com.ilham.moviesandtvshow.ui.home.fragment.tvshow
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import androidx.paging.PagedList
//import com.ilham.moviesandtvshow.data.models.TVShowModel
//import com.ilham.moviesandtvshow.data.repositories.DataRepositories
//import com.ilham.moviesandtvshow.data.repositories.Resource
//import org.junit.Test
//
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.Mockito.verify
//import org.mockito.junit.MockitoJUnitRunner
//
//
//@RunWith(MockitoJUnitRunner::class)
//class TVShowViewModelTest {
//
//    private lateinit var tvShowViewModel: TVShowViewModel
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var dataRepositories: DataRepositories
//
//    @Mock
//    private lateinit var tvObserver: Observer<Resource<PagedList<TVShowModel>>>
//
//    @Mock
//    private lateinit var tvPagedList: PagedList<TVShowModel>
//
//    @Before
//    fun setUp() {
//        tvShowViewModel = TVShowViewModel(dataRepositories)
//    }
//
//    @Test
//    fun getTVData() {
//        val dummyData = Resource.Success(tvPagedList)
//        `when`(dummyData.data?.size).thenReturn(10)
//        val tv = MutableLiveData<Resource<PagedList<TVShowModel>>>()
//        tv.value = dummyData
//
//        `when`(dataRepositories.getPopularTV(1)).thenReturn(tv)
//        val entity = tvShowViewModel.getTVData(1).value?.data
//        verify(dataRepositories).getPopularTV(1)
//        assertNotNull(entity)
//        assertEquals(10, entity?.size)
//
//        tvShowViewModel.getTVData(1).observeForever(tvObserver)
//        verify(tvObserver).onChanged(dummyData)
//    }
//}