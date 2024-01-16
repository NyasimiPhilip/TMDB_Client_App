package com.example.tmdb_client_app.data.repository

import com.example.tmdb_client_app.data.model.movie.Movie
import com.example.tmdb_client_app.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository{
    override suspend fun getMovies(): List<Movie>? {
        TODO("Not yet implemented")
    }

    override suspend fun updateMovies(): List<Movie>? {
        TODO("Not yet implemented")
    }
}