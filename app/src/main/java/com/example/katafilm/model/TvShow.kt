package com.example.katafilm.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "tv_show")
data class TvShow(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? ,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String? ,

    @ColumnInfo(name = "posterPath")
    @SerializedName("poster_path")
    var posterPath: String? ,

    @ColumnInfo(name = "backdropPath")
    @SerializedName("backdrop_path")
    var backdropPath: String? ,

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    var firstAirDate: String? ,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    var originalLanguage: String? ,

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    var popularity: String? ,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Double? ,

    @ColumnInfo("genre_ids")
    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Int
)