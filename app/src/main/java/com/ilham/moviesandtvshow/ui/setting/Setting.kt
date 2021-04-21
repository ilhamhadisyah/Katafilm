package com.ilham.moviesandtvshow.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.databinding.ActivitySettingBinding
import java.util.*

class Setting : AppCompatActivity() {
    private lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setUi()
        binding.changeLanguageView.setOnClickListener(clickListener)
    }
    private val clickListener = View.OnClickListener {
        when(it.id){
            R.id.change_language_view->{
                val setting = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(setting)
                onPause()
            }
        }
    }

    private fun setUi(){
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.action_settings)
        }
        val lang_id = Locale.getDefault().language
        binding.apply {
            languageId.text = lang_id
        }
    }

    override fun onResume() {
        super.onResume()
        setUi()
    }
}