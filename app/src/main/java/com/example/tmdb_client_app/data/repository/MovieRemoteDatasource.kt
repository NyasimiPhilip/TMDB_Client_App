package com.example.tmdb_client_app.data.repository

import com.example.tmdb_client_app.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDatasource {
    suspend fun getMovies(): Response<MovieList>
}