package com.ilham.moviesandtvshow.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.ilham.moviesandtvshow.R
import kotlinx.android.synthetic.main.activity_setting.*
import java.util.*

class Setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setUi()
        change_language_view.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener {
        when (it.id) {
            R.id.change_language_view -> {
                val setting = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(setting)
                onPause()
            }
        }
    }

    private fun setUi() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.action_settings)
        }
        val langId = Locale.getDefault().language

        language_id.text = langId

    }

    override fun onResume() {
        super.onResume()
        setUi()
    }
}