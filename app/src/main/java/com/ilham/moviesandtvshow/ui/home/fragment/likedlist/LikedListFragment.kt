package com.ilham.moviesandtvshow.ui.home.fragment.likedlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilham.moviesandtvshow.AppBase
import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.data.models.MovieModel
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.repositories.DataRepositories
import com.ilham.moviesandtvshow.data.repositories.Resource
import com.ilham.moviesandtvshow.ui.home.adapter.LikedListMovieAdapter
import com.ilham.moviesandtvshow.ui.home.adapter.LikedListTVShowAdapter
import kotlinx.android.synthetic.main.fragment_liked_list.*
import javax.inject.Inject

class LikedListFragment : Fragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var repo : DataRepositories
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_liked_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner: Spinner = menu_spinner
        spinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            view.context,
            R.array.menuDropDown,
            R.layout.custom_drop_down
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        rv_liked_list.setHasFixedSize(true)
        rv_liked_list.layoutManager = LinearLayoutManager(context)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppBase).appComponents.inject(this)
    }

    private fun observeMovie() {
        val adapter = LikedListMovieAdapter()
        rv_liked_list.adapter = adapter
        repo.getFavouriteMovies().observe(viewLifecycleOwner, Observer { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Success -> {
                        setMovieItems(movie.data as PagedList<MovieModel>, adapter)
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> Toast.makeText(
                        this.context,
                        "Connection Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }

    private fun observeTvShow() {
        val adapter = LikedListTVShowAdapter()
        rv_liked_list.adapter = adapter
        repo.getFavouriteTVShow().observe(viewLifecycleOwner, Observer { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Success -> {
                        setTvShowItems(movie.data as PagedList<TVShowModel>, adapter)
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> Toast.makeText(
                        this.context,
                        "Connection Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }

    private fun setMovieItems(pagedList: PagedList<MovieModel>, adapter: LikedListMovieAdapter) {
        adapter.submitList(pagedList)
        adapter.notifyDataSetChanged()
    }

    private fun setTvShowItems(pagedList: PagedList<TVShowModel>, adapter: LikedListTVShowAdapter) {
        adapter.submitList(pagedList)
        adapter.notifyDataSetChanged()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.getItemAtPosition(position).toString()) {
            "Movie" -> observeMovie()
            "TV Show" -> observeTvShow()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        observeMovie()
    }


}