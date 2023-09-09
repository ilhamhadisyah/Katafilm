package com.example.katafilm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class
 */
@HiltAndroidApp
class AppClass : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}