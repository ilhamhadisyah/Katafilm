package com.ilham.moviesandtvshow.viewadapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilham.moviesandtvshow.data.model.Movie
import com.ilham.moviesandtvshow.data.model.TVShow
import com.ilham.moviesandtvshow.databinding.MovieItemViewBinding
import com.ilham.moviesandtvshow.ui.detail.DetailActivity
import com.ilham.moviesandtvshow.ui.detail.TvShowDetailActivity
import kotlin.math.roundToInt

class TVShowAdapter(private var listShowTV : ArrayList<TVShow>) : RecyclerView.Adapter<TVShowAdapter.MovieViewHolder>() {

    fun setTVShowData(tv: ArrayList<TVShow>?) {
        if (tv == null) return
        this.listShowTV.clear()
        this.listShowTV.addAll(tv)
    }
    
    inner class MovieViewHolder(private val binding: MovieItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(tvSeries: TVShow   ) {
            binding.apply {
                val scoreTemp = tvSeries.score?.div(2)
                val scoreScale = (scoreTemp?.times(10.0)?.roundToInt()?.div(10.0))
                movieTitle.text = tvSeries.title
                genre.text = tvSeries.genre
                age.text = "${tvSeries.age}+"
                score.text = scoreScale.toString()
                Glide.with(itemView.context)
                    .load(tvSeries.poster)
                    .into(moviePoster)
                yearRelease.text = tvSeries.releaseYear
                itemView.setOnClickListener {
                    val position = adapterPosition
                    val intent = Intent(itemView.context,TvShowDetailActivity::class.java)
                    intent.putExtra(TvShowDetailActivity.EXTRA_MOVIE_DATA,position)
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
        holder.bind(listShowTV[position])
    }

    override fun getItemCount(): Int = listShowTV.size

}