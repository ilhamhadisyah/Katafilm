package com.ilham.moviesandtvshow.viewadapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilham.moviesandtvshow.data.model.Movie
import com.ilham.moviesandtvshow.databinding.MovieItemViewBinding
import com.ilham.moviesandtvshow.ui.detail.DetailActivity
import kotlin.math.roundToInt

class MovieAdapter(private var listMovie: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    fun setMovieData(movie: ArrayList<Movie>?) {
        if (movie == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
    }

    inner class MovieViewHolder(private val binding: MovieItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            binding.apply {
                val scoreTemp = movie.score?.div(2)
                val scoreScale = (scoreTemp?.times(10.0)?.roundToInt()?.div(10.0))
                movieTitle.text = movie.title
                genre.text = movie.genre
                age.text = "${movie.age}+"
                score.text = scoreScale.toString()
                Glide.with(itemView.context)
                    .load(movie.poster)
                    .into(moviePoster)
                yearRelease.text = movie.releaseYear
                itemView.setOnClickListener {
                    val position = adapterPosition
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE_DATA, position)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

}