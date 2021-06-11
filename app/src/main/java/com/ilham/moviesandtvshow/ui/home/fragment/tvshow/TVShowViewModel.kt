package com.ilham.moviesandtvshow.ui.home.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.repositories.DataRepositories
import com.ilham.moviesandtvshow.data.repositories.Resource
import javax.inject.Inject

class TVShowViewModel @Inject constructor(private var movieRepository: DataRepositories) :
    ViewModel() {
    fun getTVData(page: Int): LiveData<Resource<PagedList<TVShowModel>>> =
        movieRepository.getPopularTV(page)
}