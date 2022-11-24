package com.afroditexekoukoulotaki

import android.content.res.Resources
import android.util.DisplayMetrics

class Repository {

    private val TAG : String = "Repository"

    fun pixelsToPaint(displayMetrics: DisplayMetrics): Int {

        var heightPixels = displayMetrics.heightPixels // 2153
        var heightInches = heightPixels/displayMetrics.ydpi // 5.314
        val inchesPerDay = 5.905511811/365
        var numberOfDays = 1
        var inchesToDraw = 5.905511811 * numberOfDays / 365
        var paintedPixelsY: Int = (displayMetrics.ydpi * inchesToDraw).toInt()
        return paintedPixelsY
    }
}