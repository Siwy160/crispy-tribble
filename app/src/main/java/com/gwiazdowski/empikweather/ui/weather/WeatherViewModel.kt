package com.gwiazdowski.empikweather.ui.weather

import androidx.lifecycle.MutableLiveData
import com.gwiazdowski.empikweather.ui.NavigationAwareViewModel
import com.gwiazdowski.model.search.City
import com.gwiazdowski.model.weather.Forecast
import com.gwiazdowski.network.INetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(
    val networkService: INetworkService
) : NavigationAwareViewModel<WeatherArguments>() {

    private var forecastDisposable: Disposable? = null
    val forecast: MutableLiveData<Forecast> = MutableLiveData()
    val cityDetails: MutableLiveData<City> = MutableLiveData()
    val isLoadingVisible: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onArgumentsReceived(args: WeatherArguments) {
        isLoadingVisible.value = true
        forecastDisposable = networkService.getForecast(args.city.lat, args.city.lan)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    forecast.value = it
                    isLoadingVisible.value = false
                }, {
                    // TODO error handling
                    it.printStackTrace()
                    isLoadingVisible.postValue(false)
                }
            )
    }

    override fun onCleared() {
        forecastDisposable?.dispose()
        forecastDisposable = null
    }
}