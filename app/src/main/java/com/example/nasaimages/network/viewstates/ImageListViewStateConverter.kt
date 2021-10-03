package com.example.nasaimages.network.viewstates

import com.example.nasaimages.model.ImageDetails
import com.example.nasaimages.network.rx.ResponseState
import io.reactivex.functions.Function

class ImageListViewStateConverter : Function<ImageDetails, ResponseState<ImageListViewState>> {
    override fun apply(newsList: ImageDetails): ResponseState<ImageListViewState>? {

        val newsListData = ImageDetailVS(
            date = newsList.date,
            explanation = newsList.explanation,
            title = newsList.title,
            url = newsList.url

        )

        return ResponseState.Success(ImageListViewState(newsListData))
    }

}
