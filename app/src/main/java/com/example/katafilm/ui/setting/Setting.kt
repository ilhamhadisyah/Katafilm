package com.example.katafilm.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import com.example.katafilm.R
import com.example.katafilm.databinding.ActivitySettingBinding
import java.util.*

class Setting : AppCompatActivity() {

    private val binding : ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setUi()
        binding.changeLanguageView.setOnClickListener {
            val setting = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(setting)
            onPause()
        }
    }


    private fun setUi() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.action_settings)
        }
        val langId = Locale.getDefault().language

        binding.languageId.text = langId

    }

    override fun onResume() {
        super.onResume()
        setUi()
    }
}