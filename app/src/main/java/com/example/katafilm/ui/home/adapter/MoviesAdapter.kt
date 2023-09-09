package com.example.katafilm.ui.home.adapter

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.katafilm.Config
import com.example.katafilm.R
import com.example.katafilm.databinding.MovieItemViewBinding
import com.example.katafilm.model.Movie
import com.example.katafilm.utils.DateUtilities
import com.example.katafilm.utils.Genre
import com.example.katafilm.utils.Rounding


class MoviesAdapter(private val list: ArrayList<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var recyclerViewListener: RecyclerViewListener? = null

    inner class MovieViewHolder(private val binding: MovieItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            Glide.with(binding.root.context).load(Config.IMAGE_URL + movie.posterPath)
                .apply(
                    RequestOptions().override(400, 400).centerInside()
                        .placeholder(R.drawable.ic_logo)
                ).into(binding.moviePoster)
            binding.yearRelease.text = DateUtilities.getStringDate(movie.releaseDate.toString())
            binding.score.text = Rounding.roundRating(movie.voteAverage)
            binding.categoryContainer.removeAllViews()
            movie.genreIds.forEach {
                val genre = genreTextView(Genre.getGenre(it))
                binding.categoryContainer.addView(genre)
            }
            binding.root.setOnClickListener {
                recyclerViewListener?.onClickListener(movie.id)
            }

        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun genreTextView(name: String): TextView {
            val genreTv = TextView(binding.root.context)
            val params =
                LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            params.setMargins(4, 4, 4, 4)
            genreTv.layoutParams = params
            genreTv.apply {
                background = itemView.context.getDrawable(R.drawable.category_item_template)
                textSize = 10f
                setPadding(4, 0, 4, 0)
                genreTv.text = name
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }
            return genreTv
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = MovieItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//                LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateData(newList: List<Movie>) {
        list.clear()
        val sortedList = newList.sortedBy { movie -> movie.popularity }
        list.addAll(sortedList)
        notifyDataSetChanged()
    }

    fun setClickListener(action: (id: Int) -> Unit) {
        recyclerViewListener = object : RecyclerViewListener {
            override fun onClickListener(id: Int) {
                action(id)
            }
        }
    }
}