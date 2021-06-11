package com.ilham.moviesandtvshow.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.repositories.DataRepositories
import com.ilham.moviesandtvshow.data.repositories.Resource
import javax.inject.Inject

class TVShowDetailViewModel @Inject constructor(private var movieRepositories: DataRepositories) :
    ViewModel() {

    fun getTvShowDetail(id: Int): LiveData<Resource<TVShowModel>> =
        movieRepositories.getTvShowDetail(id)

    fun setFavTv(tvShowModel: TVShowModel, status: Boolean) {
        movieRepositories.setFavouriteTVShow(tvShowModel, status)
    }
}