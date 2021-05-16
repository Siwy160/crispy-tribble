package com.gwiazdowski.empikweather.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gwiazdowski.empikweather.R
import com.gwiazdowski.empikweather.ui.home.HomeFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent

class MainActivity : AppCompatActivity(), KoinScopeComponent {

    override val scope by lazy { createScope() }
    private val vm: MainActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        if (vm.backPressed().not()) {
            super.onBackPressed()
        }
    }
}