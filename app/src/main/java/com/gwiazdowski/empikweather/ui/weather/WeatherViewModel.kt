package com.gwiazdowski.empikweather.ui.weather

import android.util.Log
import com.gwiazdowski.empikweather.ui.NavigationAwareViewModel

class WeatherViewModel : NavigationAwareViewModel<WeatherArguments>() {

    override fun onArgumentsReceived(args: WeatherArguments) {
        Log.d(TAG, "onArgumentsReceived() called with: args = $args")
    }

    companion object {
        private const val TAG = "WeatherViewModel"
    }
}