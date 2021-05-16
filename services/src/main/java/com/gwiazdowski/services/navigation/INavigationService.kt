package com.gwiazdowski.services.navigation

interface INavigationService {

    fun navigateTo(target: NavigationTarget)

    /**
     * @return true if operation was handled
     */
    fun handleBackPressed(): Boolean
}