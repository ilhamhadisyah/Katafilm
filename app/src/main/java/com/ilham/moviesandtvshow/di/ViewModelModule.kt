package com.ilham.moviesandtvshow.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilham.moviesandtvshow.ui.detail.DetailViewModel
import com.ilham.moviesandtvshow.ui.detail.TVShowDetailViewModel
import com.ilham.moviesandtvshow.ui.home.fragment.likedlist.LikedListViewModel
import com.ilham.moviesandtvshow.ui.home.fragment.movie.MovieViewModel
import com.ilham.moviesandtvshow.ui.home.fragment.tvshow.TVShowViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindUserViewModel(movieViewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TVShowViewModel::class)
    abstract fun bindTVShowViewModel(tvShowViewModel: TVShowViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindMovieDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TVShowDetailViewModel::class)
    abstract fun bindTvShowDetailViewModel(tvShowDetailViewModel: TVShowDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LikedListViewModel::class)
    abstract fun bindLikedMovieViewModel(likedListViewModel: LikedListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}