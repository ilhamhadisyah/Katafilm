package com.ilham.moviesandtvshow

import android.app.Application
import com.ilham.moviesandtvshow.di.AppComponents
import com.ilham.moviesandtvshow.di.DaggerAppComponents


open class AppBase : Application() {
    val appComponents: AppComponents by lazy {
        DaggerAppComponents.factory().create(applicationContext)
    }
}