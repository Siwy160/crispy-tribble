package com.gwiazdowski.services.navigation

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import java.lang.ref.WeakReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun Application.currentActivityFragmentManager(): ReadOnlyProperty<Any?, FragmentManager?> =
    object : ReadOnlyProperty<Any?, FragmentManager?> {
        var activityRef = WeakReference<AppCompatActivity>(null)

        init {
            registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

                private fun update(activity: Activity) {
                    if (activity is AppCompatActivity && activity.hashCode() != activityRef.get()?.hashCode()) {
                        activityRef.clear()
                        activityRef = WeakReference(activity)
                    }
                }

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    update(activity)
                }

                override fun onActivityStarted(activity: Activity) {
                    update(activity)
                }

                override fun onActivityResumed(activity: Activity) {
                    update(activity)
                }

                override fun onActivityPaused(activity: Activity) {
                }

                override fun onActivityStopped(activity: Activity) {
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                }

                override fun onActivityDestroyed(activity: Activity) {
                    if (activity.hashCode() == activityRef.get()?.hashCode()) {
                        activityRef.clear()
                    }
                }
            })
        }

        override fun getValue(thisRef: Any?, property: KProperty<*>): FragmentManager? = activityRef.get()?.supportFragmentManager
    }