package com.example.nasaimages.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaimages.network.rx.ResponseState
import com.example.nasaimages.network.viewstates.ImageListViewState
import com.example.nasaimages.repository.NasaImageRepository
import com.example.nasaimages.utils.AppUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DisplayImageViewModel @Inject constructor(
    var repository: NasaImageRepository
) : ViewModel() {


    init {
        fetchNewsList()
    }
    private lateinit var subscription: Disposable

    private var nasaImage = MutableLiveData<ResponseState<ImageListViewState>>()
    val getNasaImage: LiveData<ResponseState<ImageListViewState>>
        get() = nasaImage

    fun fetchNewsList(date: String="2020-04-04") {

        subscription = repository.fetchNasaImage(AppUtils.NASA_IMAGE_URL+date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                subscription = it
            }
            .subscribe(
                { result ->
                    nasaImage.value = result
                },
                { error ->
                    error.printStackTrace()
                }
            )

    }



    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
