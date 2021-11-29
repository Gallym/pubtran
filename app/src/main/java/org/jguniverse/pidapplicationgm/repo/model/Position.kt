package org.jguniverse.pidapplicationgm.repo.model

data class Position(
        val tripId: String, // Trip Entity
        val lat: Double,
        val lon: Long,
        val tripSequenceId: Long,
        val distTraveled: Double,
        val nextStopId: String, // Stop Entity
        val prevStopId: String, // Stop Entity
        val isCanceled: Boolean
)
