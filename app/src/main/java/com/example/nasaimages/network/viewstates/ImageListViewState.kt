package com.example.nasaimages.network.viewstates

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ImageListViewState(
    val nasaImage: ImageDetailVS
) {
    companion object {
        private const val EMPTY_STRING = ""

        val EMPTY = ImageDetailVS(
            date = EMPTY_STRING,
            explanation = EMPTY_STRING,
            title = EMPTY_STRING,
            url = EMPTY_STRING
        )
    }
}

@Parcelize
data class ImageDetailVS(
    val date: String,
    val explanation: String,
    val title: String,
    val url: String
) : Parcelable
