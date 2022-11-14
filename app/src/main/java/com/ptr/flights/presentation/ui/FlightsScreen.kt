package com.ptr.flights.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ptr.flights.R
import com.ptr.flights.domain.entity.FlightDomainEntities
import com.ptr.flights.presentation.viewmodel.FlightsViewModel
import kiwi.orbit.compose.icons.Icons
import kotlin.math.roundToInt

@Composable
fun FlightsScreen(
    viewModel: FlightsViewModel
) {
    LaunchedEffect(viewModel.uiState) {
        viewModel.getFlightsOffers()
    }
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = { FlightsAppBar() },
    ) {
        FullScreenProgressBar(state.inProgress)

        if (!state.inProgress) {
            SetContentList(state.flights, it)
        }

        if (state.errorMessage.isNotEmpty()) {
            FullScreenErrorView(state.errorMessage)
        }
    }
}

@Composable
private fun FlightsAppBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.flights_screen)) }
    )

}

@Composable
fun SetContentList(
    flights: List<FlightDomainEntities.FlightDomainItem>,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        items(
            flights
        ) {
            FlightCard(flight = it)
        }

    }

}

@Composable
fun FlightCard(flight: FlightDomainEntities.FlightDomainItem) {

    val isDarkMode = isSystemInDarkTheme()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.background

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = Icons.AirplaneTakeoff,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(
                        if (isDarkMode) Color.White else Color.Black
                    )
                )

                Text(
                    text = flight.from,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = flight.departureTime,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 8.dp)
                )

            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = Icons.AirplaneLanding,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(
                        if (isDarkMode) Color.White else Color.Black
                    )
                )

                Text(
                    text = flight.to,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = flight.arrivalTime,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 8.dp)
                )

            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = Icons.Timelapse,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(
                        MaterialTheme.colors.secondaryVariant
                    )
                )

                Text(
                    text = flight.flyDuration,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )

                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    val price = flight.price
                    val priceRounded = flight.price.roundToInt()

                    Text(
                        text = "${if (price - priceRounded > 0) price else priceRounded} ${flight.currency}",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth(),
                        color = MaterialTheme.colors.secondaryVariant
                    )
                }
            }
        }

    }
}

