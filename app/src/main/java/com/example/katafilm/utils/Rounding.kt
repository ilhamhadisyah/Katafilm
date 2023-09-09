package com.example.katafilm.utils

import java.math.RoundingMode
import java.text.DecimalFormat


object Rounding {
    fun roundRating(value : Double? = 0.0) : String{
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(value).toString()

    }
}