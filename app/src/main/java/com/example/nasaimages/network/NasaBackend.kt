package com.example.nasaimages.network

import com.example.nasaimages.model.ImageDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface NasaBackend {

    @GET
    fun fetchNasaImage(@Url url: String): Single<ImageDetails>

}
