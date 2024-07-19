package dev.calex.moviesapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MoviesApp(navController: NavHostController){
    val movieViewModel: MainViewModel = viewModel()
    val viewstate by movieViewModel.movieState

    NavHost(navController = navController, startDestination = Screen.TopMoviesScreen.route){
        composable(route = Screen.TopMoviesScreen.route){
            TopMoviesScreen(viewstate = viewstate, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("mov",it)
                navController.navigate(Screen.MovieDetailsScreen.route)
            })
        }

        composable(route = Screen.MovieDetailsScreen.route){
            val movie = navController.previousBackStackEntry?.savedStateHandle?.
                    get<Movie>("mov")?: Movie("","","","","")

            MovieDetailsScreen(movie = movie)
        }
    }
}