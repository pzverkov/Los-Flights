package com.ptr.flights.data.repository.di

import android.content.Context
import androidx.room.Room
import com.ptr.flights.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    private const val DATABASE_NAME = "vehicle"

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideViewedFlightsDao(db: AppDatabase) = db.viewedFlightsDao()
}