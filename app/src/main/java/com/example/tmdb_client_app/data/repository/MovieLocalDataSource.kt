package com.example.tmdb_client_app.data.repository

import com.example.tmdb_client_app.data.model.movie.Movie

interface MovieLocalDataSource {

    suspend fun getMoviesFromDB():List<Movie>
    suspend fun saveMoviesToDB():List<Movie>
    suspend fun clearAll()
}