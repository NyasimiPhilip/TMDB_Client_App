package com.example.tmdb_client_app.data.repository.tvShow.dataSource

import com.example.tmdb_client_app.data.model.artist.Artist

interface TvShowLocalDataSource {

    suspend fun getArtistsFromDB(): List<Artist>
    suspend fun saveArtistsToDB(artists: List<Artist>)
    suspend fun clearAll()
}
