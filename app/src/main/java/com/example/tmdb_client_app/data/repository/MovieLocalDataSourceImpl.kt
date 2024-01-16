package com.example.tmdb_client_app.data.repository

import com.example.tmdb_client_app.data.db.MovieDao
import com.example.tmdb_client_app.data.model.movie.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {

    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDao.getMovies()

    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        withContext(Dispatchers.IO) {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearAll() {
        withContext(Dispatchers.IO) {
            movieDao.deleteAllMovies()
        }
    }
}
