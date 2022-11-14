package com.ptr.flights.data.repository.di

import com.ptr.flights.data.local.datasource.ViewedFlightsLocalDataSource
import com.ptr.flights.data.remote.datasource.FlightsRemoteDataSourceImpl
import com.ptr.flights.data.remote.service.ApiService
import com.ptr.flights.data.repository.FlightsRepositoryImpl
import com.ptr.flights.domain.repository.FlightsRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideFlightsRemoteDataSource(apiService: ApiService): FlightsRemoteDataSourceImpl {
        return FlightsRemoteDataSourceImpl(apiService)
    }

    @Provides
    fun provideFlightsRepository(
        remoteDataSource: FlightsRemoteDataSourceImpl,
        viewedFlightsLocalDataSource: ViewedFlightsLocalDataSource
    ): FlightsRepository = FlightsRepositoryImpl(remoteDataSource, viewedFlightsLocalDataSource)

}
