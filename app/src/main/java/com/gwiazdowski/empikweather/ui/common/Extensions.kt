package com.gwiazdowski.empikweather.ui.common

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt


fun Float.kelvinToCelsius() = this - 273.15f

fun Float.getTemperatureString() = "${(this.kelvinToCelsius()).roundToInt()}°"

fun Context.dpToPx(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
}