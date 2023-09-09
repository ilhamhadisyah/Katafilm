package com.example.katafilm.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katafilm.data.MovieRepository
import com.example.katafilm.model.Error
import com.example.katafilm.model.MovieDesc
import com.example.katafilm.model.Result
import com.example.katafilm.model.TvShowDesc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _onGetMovieData = MutableLiveData<MovieDesc?>()
    val onGetMovieData get() = _onGetMovieData

    private val _onGetShowData = MutableLiveData<TvShowDesc?>()
    val onGetShowData get() = _onGetShowData

    private val _onGetError = MutableLiveData<Error?>()
    val onGetError get() = _onGetError

    private val _onGetLoading = MutableLiveData<Boolean>()
    val onGetLoading get() = _onGetLoading

    private val _onChangedState = MutableLiveData<Int>()
    val onChangedState get() = _onChangedState

    private fun showLoading() {
        _onGetLoading.value = true
    }

    private fun dismissLoading() {
        _onGetLoading.value = false
    }

    fun setFavouriteShow(state: Int, id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.setFavouriteShow(state, id).also {
                onChangedState.postValue( Random.nextInt())
            }
        }
    }

    fun setFavouriteMovie(state: Int, id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.setFavouriteMovie(state, id).also {
                onChangedState.postValue(Random.nextInt())
            }
        }
    }


    fun getItemDetail(itemType: ItemType, typeId: Int) {
        showLoading()
        viewModelScope.launch {
            when (itemType) {
                ItemType.MOVIE -> movieRepository.fetchMovie(typeId).collect { it ->
                    when (it.status) {
                        Result.Status.SUCCESS -> {
                            _onGetMovieData.value = it.data
                            dismissLoading()
                        }

                        Result.Status.ERROR -> {
                            _onGetError.value = it.error
                            dismissLoading()
                        }

                        Result.Status.LOADING -> {

                        }
                    }

                }

                ItemType.TV_SHOW -> movieRepository.fetchShows(typeId).collect {
                    when (it.status) {
                        Result.Status.SUCCESS -> {
                            _onGetShowData.value = it.data
                            dismissLoading()
                        }

                        Result.Status.ERROR -> {
                            _onGetError.value = it.error
                            dismissLoading()
                        }

                        Result.Status.LOADING -> {

                        }
                    }
                }
            }
        }
    }
}