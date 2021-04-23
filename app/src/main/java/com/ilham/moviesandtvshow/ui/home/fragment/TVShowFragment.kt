package com.ilham.moviesandtvshow.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.data.model.TVShow
import com.ilham.moviesandtvshow.databinding.FragmentMovieBinding
import com.ilham.moviesandtvshow.databinding.FragmentTVShowBinding
import com.ilham.moviesandtvshow.viewadapter.TVShowAdapter
import com.ilham.moviesandtvshow.viewmodel.HomeViewModel

class TVShowFragment : Fragment() {

    private lateinit var binding: FragmentTVShowBinding
    private lateinit var tvAdapter: TVShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTVShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]
        viewModel.getTvData().observe(this, {
            if (it.isNullOrEmpty()) {
                Toast.makeText(context, "Data Not Found", Toast.LENGTH_SHORT).show()
            } else {
                setUi(it)
            }
        })

    }

    private fun setUi(movieList: ArrayList<TVShow>) {
        binding.apply {
            onLoadingShimmer()
            rvTvShow.layoutManager = LinearLayoutManager(context)
            tvAdapter = TVShowAdapter(arrayListOf())
            tvAdapter.apply {
                setTVShowData(movieList)
                notifyDataSetChanged()
            }
            rvTvShow.adapter = tvAdapter
            onLoadedShimmer()
        }
    }
    private fun FragmentTVShowBinding.onLoadingShimmer() {
        rvTvShow.visibility = View.GONE
        shimmerAsProgressbar.apply {
            startShimmer()
            visibility = View.VISIBLE
        }

    }
    private fun FragmentTVShowBinding.onLoadedShimmer() {
        rvTvShow.visibility = View.VISIBLE
        shimmerAsProgressbar.apply {
            visibility = View.GONE
            stopShimmer()
            clearAnimation()
        }

    }
}


