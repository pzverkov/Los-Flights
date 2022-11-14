package com.ptr.flights.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ptr.flights.domain.entity.FlightDomainEntities

@Dao
interface ViewedFlightsDao {
    @Query("SELECT * FROM viewedFlightsTable Where retrievalDate = :startDate")
    suspend fun viewedFlightsByDate(startDate: String): List<FlightDomainEntities.FlightDomainItem>

    @Query("SELECT * FROM viewedFlightsTable")
    suspend fun viewedFlights(): List<FlightDomainEntities.FlightDomainItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savedViewedFlights(flights: List<FlightDomainEntities.FlightDomainItem>)


}