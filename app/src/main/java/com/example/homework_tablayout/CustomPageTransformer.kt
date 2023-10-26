package com.example.homework_tablayout

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class CustomPageTransformer : ViewPager2.PageTransformer {
    companion object {
        private const val MIN_SCALE = 0.75f
    }

    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width

        when {
            position < -1 -> {
                page.alpha = 0f
            }
            position <= 0 -> {
                page.alpha = 1f
                page.translationX = 0f
                page.scaleX = 1f
                page.scaleY = 1f
            }

            position < 1 -> {
                page.alpha = 1f
                page.translationX = pageWidth * -position
                page.translationZ = pageWidth * -position
                val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(position))
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
            }
            position == 1f -> {
                page.alpha = 0f

            }
            else -> {
                page.alpha = 0f
                page.translationZ = -99f
            }
        }
    }
}
