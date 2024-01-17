package com.example.tmdb_client_app.data.repository.tvShow

import android.util.Log
import com.example.tmdb_client_app.data.model.movie.Movie
import com.example.tmdb_client_app.data.model.movie.MovieList
import com.example.tmdb_client_app.data.repository.movie.dataSource.MovieCacheDataSource
import com.example.tmdb_client_app.data.repository.movie.dataSource.MovieLocalDataSource
import com.example.tmdb_client_app.data.repository.movie.dataSource.MovieRemoteDatasource
import com.example.tmdb_client_app.domain.repository.MovieRepository
import retrofit2.Response

/**
 * Implementation of [MovieRepository] interface for handling movie-related data operations.
 *
 * @property movieRemoteDatasource Remote data source for movie-related operations.
 * @property movieLocalDataSource Local data source for movie-related operations.
 * @property movieCacheDataSource Cache data source for movie-related operations.
 */
class tvShowRepositoryImpl(
    private val movieRemoteDatasource: MovieRemoteDatasource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {

    /**
     * Retrieve movies from cache.
     *
     * @return List of movies if available in the cache, otherwise null.
     */
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    /**
     * Update movies by fetching new data from the API and saving it to local database and cache.
     *
     * @return List of updated movies.
     */
    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies: List<Movie> = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    /**
     * Retrieve movies from the API.
     *
     * @return List of movies fetched from the API.
     */
    suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response: Response<MovieList> = movieRemoteDatasource.getMovies()
            val body: MovieList? = response.body()
            if (body != null) {
                movieList = body.movies
            }
        } catch (exception: Exception) {
            // Log any exceptions that occur during API call
            Log.i("MyTag", exception.message.toString())
        }
        return movieList
    }

    /**
     * Retrieve movies from the local database.
     *
     * @return List of movies fetched from the local database.
     */
    suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        } catch (exception: Exception) {
            // Log any exceptions that occur during local database operations
            Log.i("MyTag", exception.message.toString())
        }
        if (movieList.size > 0) {
            return movieList
        } else {
            // If no movies are found in the local database, fetch from API and save to the database
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }

    /**
     * Retrieve movies from the cache.
     *
     * @return List of movies fetched from the cache.
     */
    suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (exception: Exception) {
            // Log any exceptions that occur during cache operations
            Log.i("MyTag", exception.message.toString())
        }
        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            // If no movies are found in the cache, fetch from API and save to the cache
            movieList = getMoviesFromAPI()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}
