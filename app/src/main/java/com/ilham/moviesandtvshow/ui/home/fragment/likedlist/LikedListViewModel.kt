package com.ilham.moviesandtvshow.ui.home.fragment.likedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.repositories.DataRepositories
import com.ilham.moviesandtvshow.data.repositories.Resource
import javax.inject.Inject

class LikedListViewModel @Inject constructor(private var dataRepositories: DataRepositories) :
    ViewModel() {
    fun getLikedMovie(page: Int): LiveData<Resource<PagedList<MovieModel>>> =
        dataRepositories.getFavouriteMovies()

    fun getLikedTVShow(page: Int): LiveData<Resource<PagedList<TVShowModel>>> =
        dataRepositories.getFavouriteTVShow()
}
