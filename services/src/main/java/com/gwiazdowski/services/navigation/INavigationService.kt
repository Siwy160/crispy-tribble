package com.gwiazdowski.services.navigation

interface INavigationService {

    fun navigateTo(target: NavigationTarget)

    fun goBack()
}