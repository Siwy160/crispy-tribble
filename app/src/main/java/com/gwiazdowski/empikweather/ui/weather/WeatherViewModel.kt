package com.gwiazdowski.empikweather.ui.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gwiazdowski.empikweather.ui.NavigationAwareViewModel
import com.gwiazdowski.model.search.City
import com.gwiazdowski.model.weather.Forecast
import com.gwiazdowski.network.INetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(
    private val networkService: INetworkService
) : NavigationAwareViewModel<WeatherArguments>() {

    private var forecastDisposable: Disposable? = null
    val forecast: MutableLiveData<Forecast> = MutableLiveData()
    val cityDetails: MutableLiveData<City> = MutableLiveData()
    val loadingVisible: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onArgumentsReceived(args: WeatherArguments) {
        loadingVisible.value = true
        cityDetails.postValue(args.city)
        forecastDisposable = networkService.getForecast(args.city.lat, args.city.lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    forecast.value = it
                    loadingVisible.value = false
                }, {
                    Log.e(TAG, "onArgumentsReceived: error while fetching city data", it)
                    loadingVisible.postValue(false)
                }
            )
    }

    override fun onCleared() {
        forecastDisposable?.dispose()
        forecastDisposable = null
    }

    private companion object {
        private const val TAG = "WeatherViewModel"
    }
}
