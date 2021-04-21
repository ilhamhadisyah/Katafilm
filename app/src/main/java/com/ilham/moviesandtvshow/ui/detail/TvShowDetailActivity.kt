package com.ilham.moviesandtvshow.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.data.model.TVShow
import com.ilham.moviesandtvshow.databinding.ActivityDetailBinding
import com.ilham.moviesandtvshow.databinding.ActivityTvDetailBinding
import com.ilham.moviesandtvshow.viewmodel.DetailViewModel
import kotlin.math.roundToInt

class TvShowDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE_DATA = "extra_movie_data"
    }
    private lateinit var binding: ActivityTvDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        setSupportActionBar(binding.toolbar)

        val position = intent.getIntExtra(EXTRA_MOVIE_DATA,0)
        binding.posterImage.setImageResource(R.drawable.poster_serenity)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        viewModel.getTVDataDetails(position).observe(this, {
            if (it.title.isNullOrEmpty()) {
                Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show()
            }else{
                bind(it)
            }
        })

    }
    @SuppressLint("SetTextI18n")
    fun bind(tvShow : TVShow){
        binding.apply {
            val scoreTemp = tvShow.score?.div(2)
            val scoreScale = (scoreTemp?.times(10.0)?.let { it.roundToInt() }?.div(10.0))
            toolbarLayout.title= tvShow.title
            tvShow.poster?.let { posterImage.setImageResource(it) }
            child.apply {
                contentMovieAge.text = "${tvShow.age}+"
                contentMovieGenre.text = tvShow.genre
                language.text = tvShow.originalLang
                movieOverview.text = tvShow.overView
                movieRating.text =scoreScale.toString()
                contentMovieTitle.text = tvShow.title
            }
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

















