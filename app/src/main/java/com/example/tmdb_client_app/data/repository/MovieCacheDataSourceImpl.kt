package com.example.tmdb_client_app.data.repository

import com.example.tmdb_client_app.data.model.movie.Movie

class MovieCacheDataSourceImpl: MovieCacheDataSource {
    private var movieList = ArrayList<Movie>()
    privage val movie
    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList

    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }
}