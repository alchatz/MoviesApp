package dev.calex.moviesapp

data class MovieState(
    val loading: Boolean = true,
    val list: List<Movie> = emptyList(),
    val error: String? = null
)
