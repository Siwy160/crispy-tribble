package com.gwiazdowski.empikweather.ui.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerSpacingItemDecoration(private val spacingPx: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = spacingPx
        outRect.right = spacingPx
        outRect.bottom = spacingPx
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = spacingPx
        } else {
            outRect.top = 0
        }
    }
}