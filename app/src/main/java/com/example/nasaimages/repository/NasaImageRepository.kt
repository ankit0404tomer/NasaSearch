package com.example.nasaimages.repository

import com.example.nasaimages.network.NasaApiFetcher
import com.example.nasaimages.network.rx.ResponseState
import com.example.nasaimages.network.rx.SchedulingStrategyFactory
import com.example.nasaimages.network.viewstates.ImageListViewState
import com.example.nasaimages.network.viewstates.ImageListViewStateConverter
import io.reactivex.Observable

class NasaImageRepository constructor(
    private val apiFetcher: NasaApiFetcher,
    private val imageListViewStateConverter: ImageListViewStateConverter,
    private val schedulingStrategyFactory: SchedulingStrategyFactory
) {


    fun fetchNasaImage(url: String): Observable<ResponseState<ImageListViewState>> {
        return apiFetcher.fetchNasaImage(url)
            .map(imageListViewStateConverter)
            .toObservable()
            .startWith(ResponseState.Loading)
            .compose(schedulingStrategyFactory.create())
    }


}
