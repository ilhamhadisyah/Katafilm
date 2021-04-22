package com.ilham.moviesandtvshow.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilham.moviesandtvshow.viewadapter.MovieAdapter
import com.ilham.moviesandtvshow.data.model.Movie
import com.ilham.moviesandtvshow.databinding.FragmentMovieBinding
import com.ilham.moviesandtvshow.viewmodel.HomeViewModel


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]
        viewModel.getMovieData().observe(this) {
            if (it.isNullOrEmpty()) {
                Toast.makeText(context, "Data Not Found", Toast.LENGTH_SHORT).show()
            } else {
                setUi(it)
            }
        }
    }

    private fun setUi(movieList: ArrayList<Movie>) {
        binding.apply {
            onLoadingShimmer()
            rvMovie.layoutManager = LinearLayoutManager(context)
            movieAdapter = MovieAdapter(arrayListOf())
            movieAdapter.apply {
                setMovieData(movieList)
                notifyDataSetChanged()
            }
            rvMovie.adapter = movieAdapter
            onLoadedShimmer()
        }
    }

    private fun FragmentMovieBinding.onLoadingShimmer() {
        rvMovie.visibility = View.GONE
        shimmerAsProgressbar.apply {
            startShimmer()
            visibility = View.VISIBLE
        }

    }
    private fun FragmentMovieBinding.onLoadedShimmer() {
        rvMovie.visibility = View.VISIBLE
        shimmerAsProgressbar.apply {
            visibility = View.GONE
            stopShimmer()
            clearAnimation()
        }

    }
}