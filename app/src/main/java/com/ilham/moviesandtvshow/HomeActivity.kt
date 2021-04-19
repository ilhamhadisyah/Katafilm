package com.ilham.moviesandtvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayout
import androidx.viewpager2.widget.ViewPager2
import com.ilham.moviesandtvshow.databinding.ActivityHomeBinding
import com.ilham.moviesandtvshow.databinding.CustomActionBarBinding

class HomeActivity : AppCompatActivity() {
    companion object {
        private val TAB_TITLES = intArrayOf(
                R.string.movie,
                R.string.tv
        )
    }

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = MainPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setDisplayShowCustomEnabled(true)
            setCustomView(R.layout.custom_action_bar)
            elevation=0f

        }
    }

}