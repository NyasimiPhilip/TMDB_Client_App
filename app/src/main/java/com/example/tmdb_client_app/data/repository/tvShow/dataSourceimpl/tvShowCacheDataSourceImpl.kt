package com.example.tmdb_client_app.data.repository.tvShow.dataSourceimpl

import com.example.tmdb_client_app.data.model.artist.Artist
import com.example.tmdb_client_app.data.model.movie.Movie
import com.example.tmdb_client_app.data.repository.artist.dataSource.ArtistCacheDataSource
import com.example.tmdb_client_app.data.repository.movie.dataSource.MovieCacheDataSource

class tvShowCacheDataSourceImpl: ArtistCacheDataSource {
    private var artistList = ArrayList<Artist>()
    override suspend fun getArtistsFromCache(): List<Artist> {
        return artistList
    }
    override suspend fun saveArtistsToCache(artists: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artists)
    }
}