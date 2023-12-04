package com.example.amphibians.network

import com.example.amphibians.model.AmphibiansPhoto
import retrofit2.http.GET

interface AmphibiansApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("photos")
    suspend fun getPhotos(): List<AmphibiansPhoto>
}