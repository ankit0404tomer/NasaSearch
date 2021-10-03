package com.example.nasaimages.di.module

import com.example.nasaimages.network.NasaApiFetcher
import com.example.nasaimages.network.NasaBackend
import com.example.nasaimages.network.rx.AndroidSchedulingStrategyFactory
import com.example.nasaimages.network.viewstates.ImageListViewStateConverter
import com.example.nasaimages.repository.NasaImageRepository
import com.example.nasaimages.utils.AppUtils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about network
 */
@Module
class NasaImageModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().newBuilder()
    }


    @Provides
    fun provideNewsFetcherNewsFetcherService(
        retrofitBuilder: Retrofit.Builder
    ): NasaBackend {

        return retrofitBuilder
            .build()
            .create(NasaBackend::class.java)
    }


    @Provides
    fun providesNewsRepository(
        apiFetcher: NasaApiFetcher,
        converter: ImageListViewStateConverter
    ): NasaImageRepository {

        return NasaImageRepository(
            apiFetcher,
            converter,
            AndroidSchedulingStrategyFactory.io()
        )
    }

    @Provides
    fun providesNewsApiFetcher(apiBackend: NasaBackend): NasaApiFetcher {
        return NasaApiFetcher(apiBackend)
    }


    @Provides
    fun providesNewsListViewStateConverter(): ImageListViewStateConverter {
        return ImageListViewStateConverter()
    }

}
