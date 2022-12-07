package com.afroditexekoukoulotaki.data.repository

import android.util.DisplayMetrics

interface PaintPixelsRepository {
    fun pixelsToPaint(displayMetrics: DisplayMetrics, numberOfDays: Int): Int
}