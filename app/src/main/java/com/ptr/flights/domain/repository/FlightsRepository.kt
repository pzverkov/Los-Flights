package com.ptr.flights.domain.repository

import com.ptr.flights.domain.entity.FlightDomainEntities

interface FlightsRepository {
    suspend fun getFlights(
        startDate: String,
        endDate: String
    ): FlightDomainEntities

    suspend fun getViewedFlights(): List<FlightDomainEntities.FlightDomainItem>
    suspend fun saveFlights(flights: List<FlightDomainEntities.FlightDomainItem>)
}
