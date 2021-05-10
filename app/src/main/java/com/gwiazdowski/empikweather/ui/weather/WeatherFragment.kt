package com.gwiazdowski.empikweather.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gwiazdowski.empikweather.databinding.WeatherFragmentBinding
import com.gwiazdowski.empikweather.ui.NavigationAwareFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : NavigationAwareFragment<WeatherArguments, WeatherViewModel, WeatherFragmentBinding>() {

    override val vm: WeatherViewModel by viewModel()

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): WeatherFragmentBinding =
        WeatherFragmentBinding.inflate(inflater, container, false)

    override fun WeatherFragmentBinding.setupBinding() {
        viewModel = vm
    }
}