package org.jguniverse.pidapplicationgm.repo.model

data class Route (
        val uid: String,
        val longName: String,
        val shortName: String,
        val desc: String,
        val agency: String,
        val color: String,
        val textColor: String,
        val type: String,
        val url: String,
        val isNight: Boolean
        )