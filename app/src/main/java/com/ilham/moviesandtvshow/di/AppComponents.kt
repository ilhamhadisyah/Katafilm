package com.ilham.moviesandtvshow.di

import android.content.Context
import com.ilham.moviesandtvshow.ui.detail.DetailActivity
import com.ilham.moviesandtvshow.ui.detail.TvShowDetailActivity
import com.ilham.moviesandtvshow.ui.home.fragment.likedlist.LikedListFragment
import com.ilham.moviesandtvshow.ui.home.fragment.movie.MovieFragment
import com.ilham.moviesandtvshow.ui.home.fragment.tvshow.TVShowFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponents {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponents
    }

    fun inject(fragment: MovieFragment)
    fun inject(fragment: TVShowFragment)
    fun inject(fragment: LikedListFragment)
    fun inject(activity: DetailActivity)
    fun inject(activity: TvShowDetailActivity)

}