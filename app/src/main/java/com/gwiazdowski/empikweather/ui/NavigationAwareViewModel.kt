package com.gwiazdowski.empikweather.ui

import androidx.lifecycle.ViewModel
import com.gwiazdowski.services.navigation.IArguments

open class NavigationAwareViewModel<A : IArguments> : ViewModel() {

    open fun onArgumentsReceived(args: A) {}

    @Suppress("UNCHECKED_CAST")
    fun setArguments(args: IArguments) = onArgumentsReceived(args as A)
}