package org.jguniverse.pidapplicationgm.repo.api

import org.jguniverse.pidapplicationgm.repo.model.Route
import org.jguniverse.pidapplicationgm.repo.model.Stop
import org.jguniverse.pidapplicationgm.repo.model.Trip
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RouteApi {

    @GET("/{id}")
    fun getById(@Path("id") id: Long) : Call<Route>

    // get trips for given route
    @GET("/{id}/trips")
    fun getTrips(@Path("id") id: Long) : Call<Set<Trip>>

    @GET("/{name}")
    fun getByNameLike(@Path("name") name: String) : Call<Set<Route>>

    companion object {
        var BASE_URL = "http://193.165.96.157:8080/api/route"

        fun create() : StopApi {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(StopApi::class.java)
        }
    }
}