package com.gwiazdowski.empikweather.ui.common

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("fadeVisible")
fun View.setFadeVisible(visible: Boolean) {
    val durationMs = 200L
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
            animate().alpha(1f).setDuration(durationMs).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    alpha = 1f
                }
            })
        } else {
            animate().alpha(0f).setDuration(durationMs).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    alpha = 1f
                    visibility = View.GONE
                }
            })
        }
    }
}

@BindingAdapter("elementSpacing")
fun RecyclerView.elementSpacing(spacing: Float) {
    addItemDecoration(RecyclerSpacingItemDecoration(spacing.toInt()))
}