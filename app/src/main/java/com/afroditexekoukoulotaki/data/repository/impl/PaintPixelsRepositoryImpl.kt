package com.afroditexekoukoulotaki.data.repository.impl

import android.util.DisplayMetrics
import com.afroditexekoukoulotaki.data.repository.PaintPixelsRepository

class PaintPixelsRepositoryImpl: PaintPixelsRepository {

    private val TAG : String = "PaintPixelsRepositoryImpl"

    override fun pixelsToPaint(displayMetrics: DisplayMetrics, numberOfDays: Int): Int {
        var heightPixels = displayMetrics.heightPixels // 2153
        var heightInches = heightPixels/displayMetrics.ydpi // 5.314
        //val inchesPerDay = 5.905511811/365
        //var numberOfDays = 1 // this will be input variable
        var inchesToDraw = 5.905511811 * numberOfDays / 365
        var paintedPixelsY: Int = (displayMetrics.ydpi * inchesToDraw).toInt()
        return paintedPixelsY
    }
}