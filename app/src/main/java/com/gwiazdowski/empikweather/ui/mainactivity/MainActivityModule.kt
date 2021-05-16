package com.gwiazdowski.empikweather.ui.mainactivity

import org.koin.dsl.module

val mainActivityModule = module {
    scope<MainActivity> {
        scoped {
            MainActivityViewModel(get())
        }
    }
}