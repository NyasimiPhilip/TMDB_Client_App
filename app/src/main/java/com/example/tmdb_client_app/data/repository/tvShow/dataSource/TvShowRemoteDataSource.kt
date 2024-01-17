package com.example.tmdb_client_app.data.repository.tvShow.dataSource

import com.example.tmdb_client_app.data.model.artist.ArtistList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getArtists(): Response<ArtistList>
}