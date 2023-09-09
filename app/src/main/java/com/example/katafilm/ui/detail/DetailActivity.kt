package com.example.katafilm.ui.detail

import android.app.ActionBar
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.katafilm.Config
import com.example.katafilm.R
import com.example.katafilm.databinding.ActivityDetailBinding
import com.example.katafilm.model.Error
import com.example.katafilm.model.MovieDesc
import com.example.katafilm.model.TvShowDesc
import com.example.katafilm.utils.DateUtilities
import com.example.katafilm.utils.Genre
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val ITEM_ID = "item_ID"
        const val ITEM_TYPE = "item_type"
    }

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val itemId = intent.getIntExtra(ITEM_ID, 0)
        val itemType = intent.getSerializableExtra(ITEM_TYPE) as ItemType

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setSupportActionBar(binding.toolbar)

        viewModel.getItemDetail(itemType, itemId)

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.onGetError.observe(
            this, ::onGetError
        )
        viewModel.onGetLoading.observe(
            this, ::onGetLoading
        )
        viewModel.onGetShowData.observe(
            this, ::onGetShowResult
        )
        viewModel.onGetMovieData.observe(
            this, ::onGetMovieResult
        )
        viewModel.onChangedState.observe(
            this, ::onChangedState
        )
    }

    private fun onChangedState(i: Int?) {
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun onGetMovieResult(movie: MovieDesc?) {
        setTitleAppBar(movie?.title)

        Glide.with(this)
            .load(Config.IMAGE_URL + movie?.backdropPath)
            .into(binding.backdropImage)

        binding.child.apply {
            contentMovieTitle.text = movie?.title
            language.isAllCaps = true
            language.text = movie?.originalLanguage
            movieOverview.text = movie?.overview
            popularity.text = movie?.popularity.toString()
            yearReleaseDetail.text = DateUtilities.getStringDate(movie?.releaseDate!!)
            voteAverage.setProgress(movie.voteAverage!!.toFloat() * 10, true)

            Glide.with(this@DetailActivity)
                .load(Config.IMAGE_URL + movie.posterPath)
                .into(imagePoster)
            binding.child.cc.removeAllViews()
            movie.genres.forEach {
                val genre = genreTextView(Genre.getGenre(it.id))
                binding.child.cc.addView(genre)
            }
            binding.addToLike.apply {
                setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    AppCompatResources.getDrawable(
                        this@DetailActivity,
                        if (movie.isFavourite == 0) R.drawable.ic_baseline_favorite_border_24
                        else R.drawable.ic_baseline_favorite_24
                    ),
                    null
                )
            }

            binding.addToLike.setOnClickListener {
                viewModel.setFavouriteMovie(
                    state = if (movie.isFavourite == 0) 1 else 0,
                    id = movie.id
                )
            }
        }
    }

    private fun onGetShowResult(show: TvShowDesc?) {
        setTitleAppBar(show?.name)
        Glide.with(this)
            .load(Config.IMAGE_URL + show?.backdropPath)
            .into(binding.backdropImage)
        binding.child.apply {
            contentMovieTitle.text = show?.name
            language.isAllCaps = true
            language.text = show?.originalLanguage
            movieOverview.text = show?.overview
            popularity.text = show?.popularity
            yearReleaseDetail.text = DateUtilities.getStringDate(show?.firstAirDate!!)
            voteAverage.setProgress(show.voteAverage!!.toFloat() * 10, true)

            Glide.with(this@DetailActivity)
                .load(Config.IMAGE_URL + show.posterPath)
                .into(imagePoster)

            binding.child.cc.removeAllViews()
            show.genres.forEach {
                val genre = genreTextView(Genre.getGenre(it.id))
                binding.child.cc.addView(genre)
            }

            binding.addToLike.apply {
                setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    AppCompatResources.getDrawable(
                        this@DetailActivity,
                        if (show.isFavourite == 0) R.drawable.ic_baseline_favorite_border_24
                        else R.drawable.ic_baseline_favorite_24
                    ),
                    null
                )
            }

            binding.addToLike.setOnClickListener {
                viewModel.setFavouriteShow(
                    state = if (show.isFavourite == 0) 1 else 0,
                    id = show.id
                )
            }
        }
    }

    private fun genreTextView(name: String): TextView {
        val genreTv = TextView(binding.root.context)
        val params =
            LinearLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
        params.setMargins(4, 4, 4, 4)
        genreTv.layoutParams = params
        genreTv.apply {
            background = AppCompatResources.getDrawable(
                this@DetailActivity,
                R.drawable.category_item_template
            )
            textSize = 10f
            setPadding(4, 0, 4, 0)
            genreTv.text = name
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        }
        return genreTv
    }

    private fun onGetLoading(loading: Boolean) {
        binding.child.contentPlaceholder.visibility = if (loading) View.VISIBLE else View.GONE
        binding.child.contentContainer.visibility = if (loading) View.GONE else View.VISIBLE

    }

    private fun onGetError(e: Error?) {
        Toast.makeText(this, e?.status_message, Toast.LENGTH_SHORT).show()
    }

    private fun setTitleAppBar(title: String?) {
        var isShow = true
        var scrollRange = -1
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = appBarLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.toolbarLayout.title = title
                isShow = true
            } else if (isShow) {
                binding.toolbarLayout.title = " "
                isShow = false
            }
        })
    }
}