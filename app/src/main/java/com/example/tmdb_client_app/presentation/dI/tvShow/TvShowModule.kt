package com.example.tmdb_client_app.presentation.dI.tvShow

import com.example.tmdb_client_app.domain.usecase.GetTvShowsUseCase
import com.example.tmdb_client_app.domain.usecase.UpdateTvShowsUseCase
import com.example.tmdb_client_app.presentation.tvShow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Hilt module for providing dependencies related to the TV show feature.
 */
@Module
@InstallIn(ActivityComponent::class)
class TvShowModule {
    @ActivityScoped
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            getTvShowsUseCase,
            updateTvShowsUseCase
        )
    }

}
