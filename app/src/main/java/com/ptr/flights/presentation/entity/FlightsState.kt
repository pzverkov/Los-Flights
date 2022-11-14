package com.ptr.flights.presentation.entity

import com.ptr.flights.domain.entity.FlightDomainEntities


data class FlightsState(
    val flights: List<FlightDomainEntities.FlightDomainItem> = listOf(),
    val errorMessage: String = "",
    val inProgress: Boolean = false
)
