package com.ilham.moviesandtvshow.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.ilham.moviesandtvshow.BuildConfig
import com.ilham.moviesandtvshow.AppBase
import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.repositories.DataRepositories
import com.ilham.moviesandtvshow.data.repositories.Resource
import com.ilham.moviesandtvshow.utils.Constants
import com.ilham.moviesandtvshow.utils.DateUtilities
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_activity_content.view.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    @Inject
    lateinit var repo : DataRepositories

    companion object {
        const val EXTRA_MOVIE_DATA = "extra_movie_data"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppBase).appComponents.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //set dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        setSupportActionBar(toolbar)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_DATA,0)
        repo.getMovieDetail(movieId).observe(this, Observer { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Success -> {
                        onLoadedData()
                        bind(movie.data)
                    }
                    is Resource.Loading -> onLoadingData()
                    is Resource.Error -> {
                        onLoadingData()
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        })
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        onLoadingData()

    }

    private fun onLoadingData() {
        child.content_placeholder.visibility = View.VISIBLE
        child.content_container.visibility = View.GONE

    }

    private fun onLoadedData() {
        child.content_placeholder.visibility = View.GONE
        child.content_container.visibility = View.VISIBLE

    }

    private fun bind(movie: MovieModel?) {
        setTitleAppBar(movie?.title)
        onLoadedData()
        if (movie != null) {
            Glide.with(this)
                .load(BuildConfig.POSTER_URL + movie.backdropPath)
                .into(backdrop_image)
            child.apply {
                content_movie_title.text = movie.title
                language.isAllCaps = true
                language.text = movie.originalLanguage
                movie_overview.text = movie.overview
                popularity.text = movie.popularity
                year_release_detail.text = DateUtilities.getStringDate(movie.releaseDate!!)
                vote_average.setProgress(movie.voteAverage!!.toFloat() * 10, true)

                Glide.with(this)
                    .load(BuildConfig.POSTER_URL + movie.posterPath)
                    .into(image_poster)

                setGenre(movie)
                setStatusButton(movie)
            }
        }
    }

    private fun View.setGenre(movie: MovieModel) {
        cc.removeAllViews()
        for (genres in 0 until movie.genre!!.size) {
            val genreItem = genreTextView(" ${Constants.getGenres(movie.genre!![genres])} ")
            cc.addView(genreItem)
        }

    }

    private fun setStatusButton(movie: MovieModel) {
        var likeStatus = movie.isFavorite
        setLikeStatus(likeStatus)
        add_to_like.setOnClickListener {
            likeStatus = !likeStatus
            repo.setFavouriteMovie(movie, likeStatus)
            setLikeStatus(likeStatus)
            Toast.makeText(this@DetailActivity, "Yaaaa", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTitleAppBar(title: String?) {
        var isShow = true
        var scrollRange = -1
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = appBarLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                toolbar_layout.title = title
                isShow = true
            } else if (isShow) {
                toolbar_layout.title = " "
                isShow = false
            }
        })
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setLikeStatus(status: Boolean) {
        if (status) {
            add_to_like.apply {
                setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    this.context.getDrawable(R.drawable.ic_baseline_favorite_24),
                    null
                )
                text = "liked"
            }
        } else {
            add_to_like.apply {
                setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    this.context.getDrawable(R.drawable.ic_baseline_favorite_border_24),
                    null
                )
                text = "like"
            }
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun genreTextView(name: String): TextView {
        val genreTv = TextView(this)
        genreTv.apply {
            background = getDrawable(R.drawable.category_item_template)
            textSize = 15f
            setPadding(6, 2, 6, 2)
            genreTv.text = name
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        }
        return genreTv
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return false
    }
}

















