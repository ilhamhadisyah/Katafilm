package com.ilham.moviesandtvshow.data.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.ilham.moviesandtvshow.data.source.local.room.Converter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieModel(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    var id: Int? = null,

    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    var overview: String? = null,

    @ColumnInfo(name = "posterPath")
    @field:SerializedName("poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "backdropPath")
    @field:SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @ColumnInfo(name = "releaseDate")
    @field:SerializedName("release_date")
    var releaseDate: String? = null,

    @ColumnInfo(name = "original_language")
    @field:SerializedName("original_language")
    var originalLanguage: String? = null,

    @ColumnInfo(name = "popularity")
    @field:SerializedName("popularity")
    var popularity: String? = null,

    @ColumnInfo(name = "vote_average")
    @field:SerializedName("vote_average")
    var voteAverage: Double? = null,

    @ColumnInfo(name = "genre_ids")
    @field:SerializedName("genre_ids")
    @TypeConverters(Converter::class)
    var genre: ArrayList<Int>? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable