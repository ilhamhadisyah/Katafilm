//package com.ilham.moviesandtvshow.ui.home.fragment.likedlist
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import androidx.paging.PagedList
//import com.ilham.moviesandtvshow.data.models.MovieModel
//import com.ilham.moviesandtvshow.data.models.TVShowModel
//import com.ilham.moviesandtvshow.data.repositories.DataRepositories
//import com.ilham.moviesandtvshow.data.repositories.Resource
//import junit.framework.Assert.assertEquals
//import junit.framework.Assert.assertNotNull
//import org.junit.Test
//
//import org.junit.Before
//import org.junit.Rule
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.Mockito.verify
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class LikedListViewModelTest {
//
//
//    private lateinit var likedListViewModel: LikedListViewModel
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var dataRepositories: DataRepositories
//
//    @Mock
//    private lateinit var movieObserver: Observer<Resource<PagedList<MovieModel>>>
//
//    @Mock
//    private lateinit var moviePagedList: PagedList<MovieModel>
//
//    @Mock
//    private lateinit var tvObserver: Observer<Resource<PagedList<TVShowModel>>>
//
//    @Mock
//    private lateinit var tvPagedList: PagedList<TVShowModel>
//
//
//    @Before
//    fun setUp() {
//        likedListViewModel = LikedListViewModel(dataRepositories)
//    }
//
//    @Test
//    fun getLikedMovie() {
//        val dummyMovies = Resource.Success(moviePagedList)
//        `when`(dummyMovies.data?.size).thenReturn(5)
//        val movies = MutableLiveData<Resource<PagedList<MovieModel>>>()
//        movies.value = dummyMovies
//
//        `when`(dataRepositories.getFavouriteMovies()).thenReturn(movies)
//        val movieEntity = likedListViewModel.getLikedMovie(1).value?.data
//        verify(dataRepositories).getFavouriteMovies()
//        assertNotNull(movieEntity)
//        assertEquals(5, movieEntity?.size)
//
//        likedListViewModel.getLikedMovie(1).observeForever(movieObserver)
//        verify(movieObserver).onChanged(dummyMovies)
//    }
//
//    @Test
//    fun getLikedTVShow() {
//        val dummyTV = Resource.Success(tvPagedList)
//        `when`(dummyTV.data?.size).thenReturn(6)
//        val tv = MutableLiveData<Resource<PagedList<TVShowModel>>>()
//        tv.value = dummyTV
//
//        `when`(dataRepositories.getFavouriteTVShow()).thenReturn(tv)
//        val tvEntity = likedListViewModel.getLikedTVShow(1).value?.data
//        verify(dataRepositories).getFavouriteTVShow()
//        assertNotNull(tvEntity)
//        assertEquals(6, tvEntity?.size)
//
//        likedListViewModel.getLikedTVShow(1).observeForever(tvObserver)
//        verify(tvObserver).onChanged(dummyTV)
//    }
//}