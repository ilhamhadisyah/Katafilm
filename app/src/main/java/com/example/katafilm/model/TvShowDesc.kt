package com.example.katafilm.model

import com.google.gson.annotations.SerializedName

data class TvShowDesc(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("genres")
    val genres: List<GenreSingle>,
    @SerializedName("backdrop_path")
    val backdropPath : String?,
    @SerializedName("first_air_date")
    var firstAirDate: String?,
    @SerializedName("original_language")
    val originalLanguage : String?,
    @SerializedName("vote_average")
    val voteAverage : Double?,
    @SerializedName("popularity")
    val popularity : String?,
    val isFavourite : Int
)
