package org.jguniverse.pidapplicationgm.repo.api

import org.jguniverse.pidapplicationgm.repo.model.Route
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleApi {

    @GET("/{id}")
    fun getById(@Path("id") id: Long) : Call<Route>

    companion object {
        var BASE_URL = "http://193.165.96.157:8080/api/vehicle"

        fun create() : StopApi {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(StopApi::class.java)
        }
    }
}