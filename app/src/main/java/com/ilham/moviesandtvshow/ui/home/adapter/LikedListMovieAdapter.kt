package com.ilham.moviesandtvshow.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilham.moviesandtvshow.BuildConfig
import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.ui.detail.DetailActivity
import com.ilham.moviesandtvshow.utils.Constants
import com.ilham.moviesandtvshow.utils.DateUtilities
import kotlinx.android.synthetic.main.movie_item_view.view.*

class LikedListMovieAdapter :
    PagedListAdapter<MovieModel, LikedListMovieAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieModel: MovieModel) {
            with(itemView) {
                year_release.text = DateUtilities.getStringDate(movieModel.releaseDate!!)
                movie_title.text = movieModel.title
                score.text = movieModel.voteAverage.toString()
                Glide.with(context)
                    .load(BuildConfig.POSTER_URL + movieModel.backdropPath)
                    .into(movie_poster)
                category_container.removeAllViews()
                for (genres in 0 until movieModel.genre!!.size) {
                    val genreItem =
                        genreTextView(" ${Constants.getGenres(movieModel.genre!![genres])} ")
                    category_container.addView(genreItem)
                }
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE_DATA, movieModel.id)
                itemView.context.startActivity(intent)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun genreTextView(name: String): TextView {
            val genreTv = TextView(itemView.context)
            genreTv.apply {
                background = itemView.context.getDrawable(R.drawable.category_item_template)
                textSize = 12f
                setPadding(4, 0, 4, 0)
                genreTv.text = name
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }
            return genreTv
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) as MovieModel)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id.toString() == newItem.id.toString()
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}