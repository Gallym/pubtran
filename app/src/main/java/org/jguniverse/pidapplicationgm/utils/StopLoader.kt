package org.jguniverse.pidapplicationgm.utils

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import org.jguniverse.pidapplicationgm.repo.model.Stop

class StopLoader(private val context: Context) {
    /**
     * Reads the list of place JSON objects in the file places.json and returns a list of Place
     * objects
     */
    fun load(): List<Stop> {
//        val itemType = object : TypeToken<List<PlaceResponse>>() {}.type
//        val reader = InputStreamReader(inputStream)
//        return gson.fromJson<List<PlaceResponse>>(reader, itemType).map {
//            it.toPlace()
//        }
        val stop1 = Stop("Václavské náměstí", LatLng(50.08167, 14.42528))
        val stop2 = Stop("Václavské náměstí", LatLng(50.08196, 14.42572))
        return listOf(stop1, stop2)
    }
}