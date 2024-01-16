package com.example.tmdb_client_app.domain.usecase

import com.example.tmdb_client_app.data.model.tvShow.TvShow
import com.example.tmdb_client_app.domain.repository.TvShowRepository

class GetTvShowUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute():List<TvShow>? = tvShowRepository.getTvShows()
}