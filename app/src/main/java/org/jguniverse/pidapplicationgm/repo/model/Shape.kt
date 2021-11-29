package org.jguniverse.pidapplicationgm.repo.model

data class Shape(
        val uid: String,
        val lat: Double,
        val lon: Double,
        val distTraveled: Double,
        val ptSequence: Int
)