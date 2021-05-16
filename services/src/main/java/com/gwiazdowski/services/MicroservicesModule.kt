package com.gwiazdowski.services

import androidx.annotation.IdRes
import com.gwiazdowski.services.navigation.INavigationService
import com.gwiazdowski.services.navigation.NavigationService
import com.gwiazdowski.services.schedulers.IRxSchedulers
import com.gwiazdowski.services.schedulers.RxSchedulers
import com.gwiazdowski.services.searchhistory.ILocalStorage
import com.gwiazdowski.services.searchhistory.LocalStorageService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun microservicesModule(@IdRes containerId: Int) = module {
    single<INavigationService> {
        NavigationService(containerId, androidApplication())
    }
    single<IRxSchedulers> {
        RxSchedulers()
    }
    single<ILocalStorage> {
        LocalStorageService(androidApplication())
    }
}