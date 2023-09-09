package com.example.katafilm.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDesc(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("genres")
    val genres: List<GenreSingle>,
    @SerializedName("backdrop_path")
    val backdropPath : String?,
    @SerializedName("release_date")
    val releaseDate :String?,
    @SerializedName("original_language")
    val originalLanguage : String?,
    @SerializedName("vote_average")
    val voteAverage : Double?,
    val isFavourite : Int
)

data class GenreSingle(val id: Int, val name: String)