package dev.calex.moviesapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _movieState = mutableStateOf(MovieState())
    val movieState: State<MovieState> = _movieState

    init {
        fetchMovies()
    }

    private fun fetchMovies(){
        viewModelScope.launch {
            try {
                val response = movieService.getMovies()
                _movieState.value = _movieState.value.copy(
                    list = response.results,
                    loading = false,
                    error = null
                )
            }catch (e: Exception){
                _movieState.value = _movieState.value.copy(
                    loading = false,
                    error = "Something went wrong! The MovieDB is currently unavailable!"
                )
                Log.d("API_Unavailable", "${e.message}")
            }
        }
    }

}

data class MovieState(
    val loading: Boolean = true,
    val list: List<Movie> = emptyList(),
    val error: String? = null
)