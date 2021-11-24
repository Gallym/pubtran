package org.jguniverse.pidapplicationgm.repo.api

import org.jguniverse.pidapplicationgm.repo.model.GeoPoint
import org.jguniverse.pidapplicationgm.repo.model.Route
import org.jguniverse.pidapplicationgm.repo.model.Stop
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface StopApi {

    @GET("/{id}")
    fun getById(@Path("id") id: Long) : Call<Stop>

    @GET("/{name}")
    fun getByNameLike(@Path("name") name: String) : Call<Set<Stop>>

    // get routes for given stop
    @GET("/{id}/routes")
    fun getRoutes(@Path("id") id: Long) : Call<Set<Route>>

    // get Stops in given area restricted by 2 points
    @GET("/{p1}/{p2}")
    fun getInArea(@Path("id") p1: GeoPoint, @Path("id") p2: GeoPoint) : Call<Set<Stop>>

    companion object {
        var BASE_URL = "http://193.165.96.157:8080/api/stop"

        fun create() : StopApi {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(StopApi::class.java)
        }
    }
}