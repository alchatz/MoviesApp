package dev.calex.moviesapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopMovies (
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int,
):Parcelable