package com.gwiazdowski.empikweather.ui.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gwiazdowski.empikweather.ui.NavigationAwareViewModel
import com.gwiazdowski.model.search.City
import com.gwiazdowski.model.weather.Forecast
import com.gwiazdowski.network.INetworkService
import com.gwiazdowski.services.schedulers.IRxSchedulers
import com.gwiazdowski.services.searchhistory.ILocalStorage
import io.reactivex.disposables.CompositeDisposable

class WeatherViewModel(
    private val networkService: INetworkService,
    private val localStorage: ILocalStorage,
    private val schedulers: IRxSchedulers
) : NavigationAwareViewModel<WeatherArguments>() {

    private val disposables = CompositeDisposable()
    val forecast: MutableLiveData<Forecast> = MutableLiveData()
    val cityDetails: MutableLiveData<City> = MutableLiveData()
    val isLoadingVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val isBookmark: MutableLiveData<Boolean> = MutableLiveData()

    override fun onArgumentsReceived(args: WeatherArguments) {
        isLoadingVisible.value = true
        cityDetails.postValue(args.city)
        disposables.add(networkService.getForecast(args.city.lat, args.city.lon)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribe(
                {
                    forecast.value = it
                    isLoadingVisible.value = false
                }, {
                    Log.e(TAG, "onArgumentsReceived: error while fetching city data", it)
                    isLoadingVisible.postValue(false)
                }
            ))
        disposables.add(
            localStorage.isBookmark(args.city)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe({
                    isBookmark.postValue(it)
                }, {
                    Log.e(TAG, "onArgumentsReceived: error while checking if city is bookmarked")
                })
        )
    }

    fun bookmarkButtonClicked() {
        val city = cityDetails.value ?: return
        disposables.add(localStorage.isBookmark(city)
            .flatMapCompletable { isBookmark ->
                if (isBookmark) {
                    this.isBookmark.postValue(false)
                    localStorage.removeBookmark(city)
                } else {
                    this.isBookmark.postValue(true)
                    localStorage.addBookmark(city)
                }
            }
            .subscribeOn(schedulers.io())
            .subscribe({
                Log.d(TAG, "bookmarkButtonClicked: bookmark state switched successfully")
            }, {
                Log.e(TAG, "bookmarkButtonClicked: error while switching bookmark state", it)
            })
        )
    }

    override fun onCleared() {
        disposables.dispose()
    }

    private companion object {
        private const val TAG = "WeatherViewModel"
    }
}
