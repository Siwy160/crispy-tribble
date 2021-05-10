package com.gwiazdowski.services.navigation

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

data class NavigationTarget(
    val targetFragment: KClass<out Fragment>,
    val arguments: IArguments
)