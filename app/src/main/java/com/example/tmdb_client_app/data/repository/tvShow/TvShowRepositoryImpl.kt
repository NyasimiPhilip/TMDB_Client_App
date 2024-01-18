package com.example.tmdb_client_app.data.repository.tvShow

import android.util.Log
import com.example.tmdb_client_app.data.model.tvShow.TvShow
import com.example.tmdb_client_app.data.model.tvShow.TvShowList
import com.example.tmdb_client_app.data.repository.tvShow.dataSource.TvShowCacheDataSource
import com.example.tmdb_client_app.data.repository.tvShow.dataSource.TvShowLocalDataSource
import com.example.tmdb_client_app.data.repository.tvShow.dataSource.TvShowRemoteDataSource
import com.example.tmdb_client_app.domain.repository.TvShowRepository
import retrofit2.Response

/**
 * Implementation of [TvShowRepository] interface for handling TV show-related data operations.
 *
 * @property tvShowRemoteDatasource Remote data source for TV show-related operations.
 * @property tvShowLocalDataSource Local data source for TV show-related operations.
 * @property tvShowCacheDataSource Cache data source for TV show-related operations.
 */
class TvShowRepositoryImpl(
    private val tvShowRemoteDatasource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {

    /**
     * Retrieve TV shows from cache.
     *
     * @return List of TV shows if available in the cache, otherwise null.
     */
    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    /**
     * Update TV shows by fetching new data from the API and saving it to the local database and cache.
     *
     * @return List of updated TV shows.
     */
    override suspend fun updateTvShows(): List<TvShow>? {
        val newListOfTvShows: List<TvShow> = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShows)
        return newListOfTvShows
    }

    /**
     * Retrieve TV shows from the API.
     *
     * @return List of TV shows fetched from the API.
     */
    suspend fun getTvShowsFromAPI(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            val response: Response<TvShowList> = tvShowRemoteDatasource.getTvShows()
            val body: TvShowList? = response.body()
            if (body != null) {
                tvShowList = body.tvShows
            }
        } catch (exception: Exception) {
            // Log any exceptions that occur during API call
            Log.i("MyTag", exception.message.toString())
        }
        return tvShowList
    }

    /**
     * Retrieve TV shows from the local database.
     *
     * @return List of TV shows fetched from the local database.
     */
    suspend fun getTvShowsFromDB(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (exception: Exception) {
            // Log any exceptions that occur during local database operations
            Log.i("MyTag", exception.message.toString())
        }
        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            // If no TV shows are found in the local database, fetch from API and save to the database
            tvShowList = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
        }
        return tvShowList
    }

    /**
     * Retrieve TV shows from the cache.
     *
     * @return List of TV shows fetched from the cache.
     */
    suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (exception: Exception) {
            // Log any exceptions that occur during cache operations
            Log.i("MyTag", exception.message.toString())
        }
        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            // If no TV shows are found in the cache, fetch from API and save to the cache
            tvShowList = getTvShowsFromAPI()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
        }
        return tvShowList
    }
}
