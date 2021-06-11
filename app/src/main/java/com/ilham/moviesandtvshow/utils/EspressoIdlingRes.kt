package com.ilham.moviesandtvshow.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingRes {
    private const val RES = "GLOBAL"
    val countingIdlingResource = CountingIdlingResource(RES)

    val idlingResource : IdlingResource
    get() = countingIdlingResource

    fun increment(){
        countingIdlingResource.increment()
    }

    fun decrement(){
        countingIdlingResource.decrement()
    }
}