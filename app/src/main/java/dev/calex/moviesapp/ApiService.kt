package dev.calex.moviesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val apiKey = BuildConfig.MoviesDB_API_KEY
private val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val movieService = retrofit.create(ApiService::class.java)

interface ApiService{
    @GET("3/movie/top_rated?api_key=${apiKey}")
    suspend fun getMovies(): TopMovies
}