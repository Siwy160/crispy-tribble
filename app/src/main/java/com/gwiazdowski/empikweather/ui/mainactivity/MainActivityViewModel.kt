package com.gwiazdowski.empikweather.ui.mainactivity

import com.gwiazdowski.services.navigation.INavigationService

class MainActivityViewModel(
    private val navigationService: INavigationService
) {

    fun backPressed(): Boolean = navigationService.handleBackPressed()
}
