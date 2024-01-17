package com.example.tmdb_client_app.data.repository.tvShow.dataSource

import com.example.tmdb_client_app.data.model.artist.Artist

interface TvShowCacheDataSource {
    suspend fun getTvShowFromCache():List<Artist>
    suspend fun saveArtistsToCache(artists:List<Artist>)

}