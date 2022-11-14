package com.ptr.flights.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ptr.flights.data.local.dao.ViewedFlightsDao
import com.ptr.flights.domain.entity.FlightDomainEntities

@Database(
    entities = [FlightDomainEntities.FlightDomainItem::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun viewedFlightsDao(): ViewedFlightsDao
}