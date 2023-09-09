package com.example.katafilm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.katafilm.databinding.FragmentLikedListBinding

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentLikedListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikedListBinding.inflate(inflater, container, false)
        return binding.root
    }
}