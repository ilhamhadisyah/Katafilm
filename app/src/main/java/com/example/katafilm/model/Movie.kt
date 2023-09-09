package com.example.katafilm.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
    @NonNull
    @PrimaryKey
    @ColumnInfo("id")
    @SerializedName("id")
    val id: Int,
    @ColumnInfo("title")
    @SerializedName("title")
    val title: String?,
    @ColumnInfo("overview")
    @SerializedName("overview")
    val overview: String?,
    @ColumnInfo("popularity")
    val popularity: Double,
    @ColumnInfo("poster_path")
    @SerializedName("poster_path")
    val posterPath: String,
    @ColumnInfo("genre_ids")
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @ColumnInfo("backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath : String?,
    @ColumnInfo("release_date")
    @SerializedName("release_date")
    val releaseDate :String?,
    @ColumnInfo("original_language")
    @SerializedName("original_language")
    val originalLanguage : String?,
    @ColumnInfo("vote_average")
    @SerializedName("vote_average")
    val voteAverage : Double?,
    @ColumnInfo("isFavourite")
    val isFavourite : Int

)