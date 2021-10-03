package com.example.nasaimages.network

import com.example.nasaimages.model.ImageDetails
import io.reactivex.Single

class NasaApiFetcher constructor(private val apiBackend: NasaBackend) : NasaFetcher {

    override fun fetchNasaImage(url: String): Single<ImageDetails> {
        return apiBackend.fetchNasaImage(url)
    }
}
