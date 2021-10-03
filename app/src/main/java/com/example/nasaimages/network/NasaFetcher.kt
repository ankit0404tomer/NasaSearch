package com.example.nasaimages.network

import com.example.nasaimages.model.ImageDetails
import io.reactivex.Single

interface NasaFetcher {

    fun fetchNasaImage(newsUrl: String): Single<ImageDetails>
}
