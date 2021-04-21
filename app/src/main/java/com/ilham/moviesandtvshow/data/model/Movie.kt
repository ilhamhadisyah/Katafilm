package com.ilham.moviesandtvshow.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var title: String? = null,
    var poster: Int? = 0,
    var originalLang: String? = null,
    var score: Double? = null,
    var overView: String? = null,
    var genre: String? = null,
    var age: String? = null,
    var releaseYear: String? = null
) : Parcelable