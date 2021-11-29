package org.jguniverse.pidapplicationgm.repo.model

import java.sql.Timestamp

data class Vehicle(
        val tripId: String, // Trip Entity
        val originRouteName: String,
        val cisLineId: String,
        val cisTripNumber: Int,
        val lat: Double,
        val lon: Double,
        val speed: Int,
        val distTraveled: Double,
        val tracking: Boolean,
        val tripSequenceId: Int,
        val delay: Int,
        val delayLastStop: Int,
        val isCanceled: Boolean,
        val lastStopId: String, // (previous stop) Stop Entity
        val lastStopDeparture: Timestamp, // (previous stop)
        val nextStopId: String, // Stop Entity
        val nextStopArrival: Timestamp,
        val vehicleType: VehicleType,
        val allPosition: Int
)
