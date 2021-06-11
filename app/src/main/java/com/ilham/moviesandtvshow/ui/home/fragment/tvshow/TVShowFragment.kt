package com.ilham.moviesandtvshow.ui.home.fragment.tvshow

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
import com.ilham.moviesandtvshow.data.models.TVShowModel
import com.ilham.moviesandtvshow.data.repositories.Resource
import com.ilham.moviesandtvshow.di.ViewModelFactory
import com.ilham.moviesandtvshow.ui.home.adapter.TVShowPagedAdapter
import kotlinx.android.synthetic.main.fragment_t_v_show.*
import javax.inject.Inject

class TVShowFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val tvShowViewModel: TVShowViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_t_v_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_tv_show.setHasFixedSize(true)
        rv_tv_show.layoutManager = LinearLayoutManager(context)
        setUi()
    }

    private fun setUi() {
        val adapter = TVShowPagedAdapter()
        rv_tv_show.adapter = adapter
        rv_tv_show.hasFixedSize()
        tvShowViewModel.getTVData(1).observe(viewLifecycleOwner, Observer { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Success -> {
                        onLoadedShimmer()
                        showList(tvShow.data as PagedList<TVShowModel>, adapter)
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

    private fun onLoadingShimmer() {
        rv_tv_show.visibility = View.GONE
        shimmer_as_progressbar.apply {
            startShimmer()
            visibility = View.VISIBLE
        }

    }

    private fun showList(listMovie: PagedList<TVShowModel>, adapter: TVShowPagedAdapter) {
        adapter.submitList(listMovie)
        adapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppBase).appComponents.inject(this)
    }

    private fun onLoadedShimmer() {
        rv_tv_show.visibility = View.VISIBLE
        shimmer_as_progressbar.apply {
            visibility = View.GONE
            stopShimmer()
            clearAnimation()
        }

    }


}


