package com.ptr.flights.data.remote.datasource

import com.ptr.flights.data.constant.NetworkStatus
import com.ptr.flights.data.remote.entity.FlightResponse
import com.ptr.flights.data.remote.service.ApiService
import com.ptr.flights.data.util.safeApiCall

class FlightsRemoteDataSourceImpl(private val apiService: ApiService) {

    suspend fun getFlights(startDate: String, endDate: String): NetworkStatus<FlightResponse> =
        safeApiCall { apiService.getFlights(dateFrom = startDate, dateTo = endDate) }
}