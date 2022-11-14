package com.ptr.flights.data.local.datasource

import com.ptr.flights.data.local.dao.ViewedFlightsDao
import com.ptr.flights.domain.entity.FlightDomainEntities
import javax.inject.Inject

class ViewedFlightsLocalDataSource @Inject constructor(private val viewedFlightsDao: ViewedFlightsDao) {

    suspend fun saveFlights(flights: List<FlightDomainEntities.FlightDomainItem>) {
        return viewedFlightsDao.savedViewedFlights(flights)
    }
    suspend fun viewedFlights(): List<FlightDomainEntities.FlightDomainItem> {
        return viewedFlightsDao.viewedFlights()
    }

    suspend fun viewedFlightsByDate(startDate: String): List<FlightDomainEntities.FlightDomainItem> {
        return viewedFlightsDao.viewedFlightsByDate(startDate)
    }
}
