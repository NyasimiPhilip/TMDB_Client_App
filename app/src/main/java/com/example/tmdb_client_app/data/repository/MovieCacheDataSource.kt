package com.example.tmdb_client_app.data.repository

import com.example.tmdb_client_app.data.model.movie.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache():List<Movie>
    suspend fun saveMoviesToCache(movies:List<Movie>)

}