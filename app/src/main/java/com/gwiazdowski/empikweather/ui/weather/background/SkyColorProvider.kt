package com.gwiazdowski.empikweather.ui.weather.background

import android.content.Context
import androidx.core.content.ContextCompat
import com.gwiazdowski.empikweather.R
import java.util.*

class SkyColorProvider(private val context: Context) {

    val colors = listOf(
        SkyColor(
            top = R.color.sunrise_top_color.toColor(),
            bottom = R.color.sunrise_bottom_color.toColor(),
            isDay = true,
            dayPercent = 0f
        ),
        SkyColor(
            top = R.color.morning_top_color.toColor(),
            bottom = R.color.morning_bottom_color.toColor(),
            isDay = true,
            dayPercent = 0.2f
        ),
        SkyColor(
            top = R.color.afternoon_top_color.toColor(),
            bottom = R.color.afternoon_bottom_color.toColor(),
            isDay = true,
            dayPercent = 0.6f
        ),
        SkyColor(
            top = R.color.sunset_top_color.toColor(),
            bottom = R.color.sunset_bottom_color.toColor(),
            isDay = false,
            dayPercent = 0f
        ),
        SkyColor(
            top = R.color.dusk_top_color.toColor(),
            bottom = R.color.dusk_bottom_color.toColor(),
            isDay = false,
            dayPercent = 0.1f
        ),
        SkyColor(
            top = R.color.night_top_color.toColor(),
            bottom = R.color.night_bottom_color.toColor(),
            isDay = false,
            dayPercent = 0.5f
        ),
        SkyColor(
            top = R.color.dawn_top_color.toColor(),
            bottom = R.color.dawn_bottom_color.toColor(),
            isDay = false,
            dayPercent = 0.8f
        )
    )

    fun getColorForTimeOfDay(time: Date, sunrise: Date, sunset: Date): SkyColor {
        val dayProgress = time.getDayProgress()
        val sunriseProgress = sunrise.getDayProgress()
        val sunsetProgress = sunset.getDayProgress()
        val isDay = dayProgress > sunriseProgress && dayProgress < sunsetProgress
        val dayPercent = when {
            isDay.not() && dayProgress < sunriseProgress -> (1f - sunsetProgress) + sunriseProgress - dayProgress
            isDay.not() && dayProgress > sunsetProgress -> dayProgress - sunsetProgress
            else -> dayProgress - sunriseProgress

        }

        val firstColor = colors
            .filter { it.isDay == isDay }
            .firstOrNull { it.dayPercent < dayProgress }
            ?: colors.last { it.isDay != isDay }
        val secondColor = colors
            .filter { it.isDay == isDay }
            .firstOrNull { it.dayPercent >= dayPercent }
            ?: colors.first { it.isDay != isDay }
        // TODO color blending depending on time
        return firstColor
    }

    private fun Date.getDayProgress(): Float {
        val calendar = Calendar.getInstance()
        calendar.time = this
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        return hours.toFloat() / 24f
    }

    private fun Int.toColor() = ContextCompat.getColor(context, this)
}