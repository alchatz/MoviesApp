package dev.calex.moviesapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val overview: String,
    val release_date: String,
    val backdrop_path: String,
    val poster_path: String
):Parcelable