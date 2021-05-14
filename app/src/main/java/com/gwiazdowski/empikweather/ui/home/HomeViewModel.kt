package com.gwiazdowski.empikweather.ui.home

import android.util.Log
import com.gwiazdowski.empikweather.ui.NavigationAwareViewModel
import com.gwiazdowski.empikweather.ui.weather.WeatherArguments
import com.gwiazdowski.empikweather.ui.weather.WeatherFragment
import com.gwiazdowski.model.search.City
import com.gwiazdowski.network.INetworkService
import com.gwiazdowski.services.navigation.INavigationService
import com.gwiazdowski.services.navigation.NavigationTarget
import io.reactivex.disposables.Disposable


class HomeViewModel(
    private val networkService: INetworkService,
    private val navigationService: INavigationService
) : NavigationAwareViewModel<HomeArguments>() {

    private var weahterDisposable: Disposable? = null
    

    fun citySearchQueryChanged(newQuery: String) {
        Log.d(TAG, "citySearchQueryChanged() called with: newQuery = $newQuery")
    }

    fun citySearchQuerySubmit(newQuery: String) {
        val city = City("", 0.0, 0.0)
        navigationService.navigateTo(
            NavigationTarget(
                WeatherFragment::class,
                WeatherArguments(city)
            )
        )
    }

    override fun onCleared() {
        weahterDisposable?.dispose()
        weahterDisposable = null
    }

    private companion object {
        private const val TAG = "HomeViewModel"
    }
}