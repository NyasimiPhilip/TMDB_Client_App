package com.example.tmdb_client_app.presentation.dI.artist

import com.example.tmdb_client_app.domain.usecase.GetArtistsUseCase
import com.example.tmdb_client_app.domain.usecase.UpdateArtistsUseCase
import com.example.tmdb_client_app.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Hilt module for providing dependencies related to the Artist feature.
 */
@Module
@InstallIn(ActivityComponent::class)
class ArtistModule {
    @ActivityScoped
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(
            getArtistsUseCase,
            updateArtistsUseCase
        )
    }

}