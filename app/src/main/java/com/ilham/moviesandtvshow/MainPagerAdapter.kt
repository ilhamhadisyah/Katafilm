package com.ilham.moviesandtvshow

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(appCompatActivity: AppCompatActivity) :
    FragmentStateAdapter(appCompatActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TVShowFragment()
        }
        return fragment as Fragment
    }
}