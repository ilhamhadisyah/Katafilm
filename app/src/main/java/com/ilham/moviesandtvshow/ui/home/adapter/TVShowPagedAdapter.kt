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
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.ui.detail.TvShowDetailActivity
import com.ilham.moviesandtvshow.utils.Constants
import com.ilham.moviesandtvshow.utils.DateUtilities
import kotlinx.android.synthetic.main.movie_item_view.view.category_container
import kotlinx.android.synthetic.main.movie_item_view.view.movie_poster
import kotlinx.android.synthetic.main.movie_item_view.view.movie_title
import kotlinx.android.synthetic.main.movie_item_view.view.score
import kotlinx.android.synthetic.main.movie_item_view.view.year_release

class TVShowPagedAdapter :
    PagedListAdapter<TVShowModel, TVShowPagedAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShowModel: TVShowModel) {
            with(itemView) {
                year_release.text = DateUtilities.getStringDate(tvShowModel.first_air_date!!)
                movie_title.text = tvShowModel.name
                score.text = tvShowModel.voteAverage.toString()
                Glide.with(context)
                    .load(BuildConfig.POSTER_URL + tvShowModel.backdropPath)
                    .into(movie_poster)
                category_container.removeAllViews()
                for (genres in 0 until tvShowModel.genre!!.size) {
                    val genreItem = Constants.getGenres(tvShowModel.genre!![genres])
                    if (!genreItem.isNullOrEmpty()) {
                        category_container.addView(genreTextView(" $genreItem "))
                    }

                }
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                intent.putExtra(TvShowDetailActivity.EXTRA_MOVIE_DATA, tvShowModel.id)
                itemView.context.startActivity(intent)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun genreTextView(name: String): TextView {
            val tvGenre = TextView(itemView.context)
            tvGenre.apply {
                background = itemView.context.getDrawable(R.drawable.category_item_template)
                textSize = 12f
                text = name
                setPadding(4, 0, 4, 0)
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }
            return tvGenre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShowModel)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowModel>() {
            override fun areItemsTheSame(oldItem: TVShowModel, newItem: TVShowModel): Boolean {
                return oldItem.id.toString() == newItem.id.toString()
            }

            override fun areContentsTheSame(oldItem: TVShowModel, newItem: TVShowModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}