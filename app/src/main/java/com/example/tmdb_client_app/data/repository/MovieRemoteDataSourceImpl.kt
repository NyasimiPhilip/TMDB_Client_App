package com.example.tmdb_client_app.data.repository

import com.example.tmdb_client_app.data.api.TmDbService
import com.example.tmdb_client_app.data.model.movie.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TmDbService,
    private val apiKey: String): MovieRemoteDatasource {
    override suspend fun getMovies(): Response<MovieList> =
        tmdbService.getPopularMovies(apiKey)

}