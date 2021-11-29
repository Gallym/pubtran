package org.jguniverse.pidapplicationgm.repo.api

import org.jguniverse.pidapplicationgm.repo.model.Position
import org.jguniverse.pidapplicationgm.repo.model.Route
import org.jguniverse.pidapplicationgm.repo.model.Shape
import org.jguniverse.pidapplicationgm.repo.model.Vehicle
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TripApi {

    @GET("/{id}")
    fun getById(@Path("id") id: Long) : Call<Route>

    // get vehicles for given trip
    @GET("/{id}/vehicles")
    fun getVehicles(@Path("id") id: Long) : Call<Set<Vehicle>>

    // get geoshape i.e. set of geopoints to represent path(shape)
    @GET("/{id}/shape")
    fun getShape(@Path("id") id: Long) : Call<Shape>

    // get current position
    @GET("/{id}/position")
    fun getPosition(@Path("id") id: Long) : Call<Position>


    companion object {
        var BASE_URL = "http://193.165.96.157:8080/api/trip"

        fun create() : StopApi {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(StopApi::class.java)
        }
    }
}