package com.example.tmdb_client_app.presentation.dI.core
import com.example.tmdb_client_app.BuildConfig
import com.example.tmdb_client_app.data.api.TMDBService
import com.example.tmdb_client_app.data.repository.artist.dataSource.ArtistRemoteDataSource
import com.example.tmdb_client_app.data.repository.artist.dataSourceimpl.ArtistRemoteDataSourceImpl
import com.example.tmdb_client_app.data.repository.movie.dataSource.MovieRemoteDataSource
import com.example.tmdb_client_app.data.repository.movie.dataSourceimpl.MovieRemoteDataSourceImpl
import com.example.tmdb_client_app.data.repository.tvShow.dataSource.TvShowRemoteDataSource
import com.example.tmdb_client_app.data.repository.tvShow.dataSourceimpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideApiKey(): String = BuildConfig.API_KEY


    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService, apiKey: String): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(tmdbService, BuildConfig.API_KEY)
    }

    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(tmdbService: TMDBService, apiKey: String): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(tmdbService, BuildConfig.API_KEY)
    }

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(tmdbService: TMDBService, apiKey: String): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(tmdbService, BuildConfig.API_KEY)
    }
}
