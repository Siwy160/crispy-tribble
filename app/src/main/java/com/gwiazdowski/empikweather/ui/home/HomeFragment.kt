package com.gwiazdowski.empikweather.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gwiazdowski.empikweather.databinding.HomeFragmentBinding
import com.gwiazdowski.empikweather.ui.NavigationAwareFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : NavigationAwareFragment<HomeArguments, HomeViewModel, HomeFragmentBinding>() {

    override val vm: HomeViewModel by viewModel()

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        HomeFragmentBinding.inflate(inflater, container, false)

    override fun HomeFragmentBinding.setupBinding() {
        viewModel = vm
    }
}