package dev.calex.moviesapp

sealed class Screen (val route: String) {
    object TopMoviesScreen: Screen("top_movies_screen")
    object MovieDetailsScreen: Screen("movie_details_screen")
}