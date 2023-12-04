package com.example.amphibians.data

import com.example.amphibians.model.AmphibiansPhoto
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    /** Fetches list of MarsPhoto from marsApi */
    suspend fun getAmphibians(): List<AmphibiansPhoto>
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */
class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getAmphibians(): List<AmphibiansPhoto> = amphibiansApiService.getPhotos()
}