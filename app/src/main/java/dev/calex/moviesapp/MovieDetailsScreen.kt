package dev.calex.moviesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MovieDetailsScreen(movie: Movie) {
    val imageDetailsSrc = "https://image.tmdb.org/t/p/"
    val imgSize = "w342"
    val finalImgPath = imageDetailsSrc+imgSize+'/'+movie.backdrop_path

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Top) {
        Image(
            painter = rememberAsyncImagePainter(finalImgPath),
            contentDescription = "Backdrop image for the movie ${movie.title}",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .weight(1 / 3f))
        Text(text = movie.title,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = movie.overview,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxSize()
                .weight(2 / 3f)
                .verticalScroll(rememberScrollState()),
        )

    }
}