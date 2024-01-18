package com.example.tmdb_client_app.presentation.dI

import com.example.tmdb_client_app.data.repository.movie.dataSource.MovieCacheDataSource
import com.example.tmdb_client_app.data.repository.movie.dataSourceimpl.MovieCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideMovieCacheDataSource(): MovieCacheDataSource {

        return MovieCacheDataSourceImpl()
    }
}