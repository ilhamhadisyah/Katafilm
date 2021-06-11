package com.ilham.moviesandtvshow.ui.home.fragment.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilham.moviesandtvshow.AppBase
import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.repositories.Resource
import com.ilham.moviesandtvshow.di.ViewModelFactory
import com.ilham.moviesandtvshow.ui.home.adapter.MoviePagedAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject


class MovieFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val movieViewModel: MovieViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movie.setHasFixedSize(true)
        rv_movie.layoutManager = LinearLayoutManager(context)
        setUi()
    }

    private fun setUi() {
        val adapter = MoviePagedAdapter()
        rv_movie.adapter = adapter
        rv_movie.hasFixedSize()
        movieViewModel.getDataMovie(1).observe(viewLifecycleOwner, Observer { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Success -> {
                        onLoadedShimmer()
                        showList(movie.data as PagedList<MovieModel>, adapter)
                    }
                    is Resource.Loading -> onLoadingShimmer()
                    is Resource.Error -> {
                        onLoadingShimmer()
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })
    }

    private fun showList(listMovie: PagedList<MovieModel>, adapter: MoviePagedAdapter) {
        adapter.submitList(listMovie)
        adapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppBase).appComponents.inject(this)
    }

    private fun onLoadingShimmer() {
        rv_movie.visibility = View.GONE
        shimmer_as_progressbar.apply {
            startShimmer()
            visibility = View.VISIBLE
        }

    }

    private fun onLoadedShimmer() {
        rv_movie.visibility = View.VISIBLE
        shimmer_as_progressbar.apply {
            visibility = View.GONE
            stopShimmer()
            clearAnimation()
        }

    }
}