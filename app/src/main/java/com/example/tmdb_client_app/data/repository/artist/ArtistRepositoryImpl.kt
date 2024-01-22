package com.example.tmdb_client_app.data.repository.artist

import android.util.Log
import com.example.tmdb_client_app.data.model.artist.Artist
import com.example.tmdb_client_app.data.model.artist.ArtistList
import com.example.tmdb_client_app.data.model.movie.Movie
import com.example.tmdb_client_app.data.repository.artist.dataSource.ArtistCacheDataSource
import com.example.tmdb_client_app.data.repository.artist.dataSource.ArtistLocalDataSource
import com.example.tmdb_client_app.data.repository.artist.dataSource.ArtistRemoteDataSource
import com.example.tmdb_client_app.domain.repository.ArtistRepository
import retrofit2.Response

/**
 * Implementation of [ArtistRepository] interface for handling artist-related data operations.
 *
 * @property artistRemoteDataSource Remote data source for artist-related operations.
 * @property artistLocalDataSource Local data source for artist-related operations.
 * @property artistCacheDataSource Cache data source for artist-related operations.
 */
class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {

    /**
     * Retrieve artists from cache.
     *
     * @return List of artists if available in the cache, otherwise null.
     */
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    /**
     * Update artists by fetching new data from the API and saving it to the local database and cache.
     *
     * @return List of updated artists.
     */
    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtists: List<Artist> = getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newListOfArtists)
        artistCacheDataSource.saveArtistsToCache(newListOfArtists)
        return newListOfArtists
    }

    /**
     * Retrieve artists from the API.
     *
     * @return List of artists fetched from the API.
     */
    suspend fun getArtistsFromAPI(): List<Artist> {
        var artistList: List<Artist> = emptyList()
        try {
            val response: Response<ArtistList> = artistRemoteDataSource.getArtists()
            val body: ArtistList? = response.body()
            if (body != null) {
                artistList = body.artists
            }
        } catch (exception: Exception) {
            // Log any exceptions that occur during the API call
            Log.i("MyTag", exception.message.toString())
        }
        return artistList
    }

    /**
     * Retrieve artists from the local database.
     *
     * @return List of artists fetched from the local database.
     */
    suspend fun getArtistsFromDB(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistsFromDB()
        } catch (exception: Exception) {
            // Log any exceptions that occur during local database operations
            Log.i("MyTag", exception.message.toString())
        }
        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            // If no artists are found in the local database, fetch from API and save to the database
            artistList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDB(artistList)
        }
        return artistList
    }

    /**
     * Retrieve artists from the cache.
     *
     * @return List of artists fetched from the cache.
     */
    suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistsFromCache()
        } catch (exception: Exception) {
            // Log any exceptions that occur during cache operations
            Log.i("MyTag", exception.message.toString())
        }
        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            // If no artists are found in the cache, fetch from API and save to the cache
            artistList = getArtistsFromAPI()
            artistCacheDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }
}
