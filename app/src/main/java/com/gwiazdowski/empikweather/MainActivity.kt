package com.gwiazdowski.empikweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gwiazdowski.empikweather.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commitNow()
        }
    }
}