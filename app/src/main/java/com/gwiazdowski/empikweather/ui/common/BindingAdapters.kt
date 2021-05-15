package com.gwiazdowski.empikweather.ui.common

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("fadeVisible")
fun View.setFadeVisible(visible: Boolean) {
    val targetVisibility = if (visible) View.VISIBLE else View.GONE
    if (visibility == targetVisibility) {
        return
    }
    if (tag == null) {
        tag = true
        visibility = targetVisibility
    } else {
        animate().cancel()
        if (visible) {
            visibility = View.VISIBLE
            alpha = 0f
            animate().alpha(1f).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    alpha = 1f
                }
            })
        } else {
            animate().alpha(0f).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    alpha = 1f
                    visibility = View.GONE
                }
            })
        }
    }
}
