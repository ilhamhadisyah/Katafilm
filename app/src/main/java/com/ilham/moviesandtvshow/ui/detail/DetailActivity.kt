package com.ilham.moviesandtvshow.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.data.model.Movie
import com.ilham.moviesandtvshow.databinding.ActivityDetailBinding
import com.ilham.moviesandtvshow.viewmodel.DetailViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_DATA = "extra_movie_data"
    }

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        setSupportActionBar(binding.toolbar)

        val position = intent.getIntExtra(EXTRA_MOVIE_DATA, 0)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]
        onLoadingData()
        viewModel.getMovieDataDetails(position).observe(this, {
            if (it.title.isNullOrEmpty()) {
                Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show()
            } else {
                onLoadedData()
                bind(it)

            }
        })
    }

    private fun onLoadingData() {
        lifecycleScope.launch {
            binding.apply {
                imagePlaceholder.visibility = View.VISIBLE
                posterImageContainer.visibility = View.GONE
                child.apply {
                    contentPlaceholder.visibility = View.VISIBLE
                    contentContainer.visibility = View.GONE

                }
            }
        }
    }

    private fun onLoadedData() {
        lifecycleScope.launch {
        binding.apply {
            imagePlaceholder.visibility = View.GONE
            posterImageContainer.visibility = View.VISIBLE

                child.apply {
                    contentPlaceholder.visibility = View.GONE
                    contentContainer.visibility = View.VISIBLE
                }

            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(movie: Movie) {

        binding.apply {
            val scoreTemp = movie.score?.div(2)
            val scoreScale = (scoreTemp?.times(10.0)?.roundToInt()?.div(10.0))
            toolbarLayout.title = movie.title
            movie.poster?.let { posterImage.setImageResource(it) }
            //lifecycleScope.launch {
                child.apply {
                    contentMovieAge.text = "${movie.age}+"
                    contentMovieGenre.text = movie.genre
                    language.text = movie.originalLang
                    yearReleaseDetail.text = movie.releaseYear
                    movieOverview.text = movie.overView
                    movieRating.text = scoreScale.toString()
                    contentMovieTitle.text = movie.title
                }
            //}
        }


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

















