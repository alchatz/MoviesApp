package dev.calex.moviesapp

import android.widget.GridView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun TopMoviesScreen(modifier: Modifier = Modifier, viewstate: MovieState, navigateToDetail: (Movie) -> Unit){
    val movieViewModel: MainViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewstate.error !=null -> {
                Text(text = "Error occured! Error: ${viewstate.error}",
                    modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                MovieOverviewScreen(movies = viewstate.list, navigateToDetail)
            }
        }
    }
}

@Composable
fun MovieOverviewScreen(movies: List<Movie>, navigateToDetail: (Movie) -> Unit){
    LazyColumn (modifier = Modifier.fillMaxSize()) {
        items(movies){
            movie ->
            MovieOverviewItem(movie = movie, navigateToDetail)
        }
    }
}

@Composable
fun MovieOverviewItem(movie: Movie, navigateToDetail: (Movie) -> Unit){
    val imageOverviewSrc = "https://image.tmdb.org/t/p/"
    val imgSize = "w185"
    val finalImgPath = imageOverviewSrc+imgSize+'/'+movie.poster_path

    Column (modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { navigateToDetail(movie) },
        horizontalAlignment = Alignment.CenterHorizontally) {

        Card {
            Row (modifier = Modifier.fillMaxSize(),
                ){
                Image(
                    painter = rememberAsyncImagePainter(finalImgPath),
                    contentDescription = "Poster image for the movie ${movie.title}",
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .weight(1/4f)
                )

                Column (modifier = Modifier
                    .padding(8.dp)
                    .weight(3/4f)
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.Start) {
                    Text(
                        text= movie.title,
                        color = Color.Black,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp),
                        modifier = Modifier.padding(top=4.dp)
                    )

                    Text(
                        text= movie.release_date,
                        color = Color.Black,
                        modifier = Modifier.padding(top=8.dp)
                    )
                }
            }
        }
    }
}