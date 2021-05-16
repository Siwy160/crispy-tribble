package com.gwiazdowski.services.navigation

import android.app.Application
import androidx.annotation.IdRes
import kotlin.reflect.full.primaryConstructor

internal class NavigationService(
    @IdRes private val containerId: Int,
    application: Application
) : INavigationService {

    private val fragmentManager by application.currentActivityFragmentManager()

    override fun navigateTo(target: NavigationTarget) {
        val targetFragment = target.targetFragment.primaryConstructor!!.call()
        (targetFragment as IView).setArguments(target.arguments)
        fragmentManager!!
            .beginTransaction()
            .replace(containerId, targetFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun handleBackPressed() = goBack()

    private fun goBack(): Boolean {
        if (fragmentManager!!.backStackEntryCount == 0) {
            return false
        }
        fragmentManager!!.popBackStack()
        return true
    }
}
