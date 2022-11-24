package com.afroditexekoukoulotaki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * In this project user will input the date she cut her hair and after save
 * she will track how long her hair grows every day.
 * Y axis of painted pixels represents the real life length of her hair
 * has grown from the start of the haircut date.
 *
 * The first time she opens the app cutDate = currentDate
 * MainActivity and InputFragment share the HairViewModel
 * When FloatingActionButton is pressed, InputFragment is created
 *
 * Please help me with navigation,
 * how to open the fragment
 * where to initialize cutDate as currentDate
 * how to save editTextDate as variable
 * xml needs improvement
 */
class MainActivity : AppCompatActivity() {
     val TAG: String = "MainActivity"
    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var displayMetrics: DisplayMetrics = resources.displayMetrics

        var viewModel = ViewModelProvider(
            this,
            ViewModelFactory(repository)
        )[HairViewModel::class.java]

        var paintedPixels = findViewById<View>(R.id.paintedPixels)
        //paintedPixels.layoutParams = LayoutParams(100, 100)
        var layoutParams = paintedPixels.layoutParams
        //lp.height = paintedPixelsY
        viewModel.pixelsToPaint(displayMetrics)
        viewModel.numPixels.observe(this){
            layoutParams.height = it

            Log.d(TAG, "" + displayMetrics.density + " " + displayMetrics.densityDpi + " " + displayMetrics.heightPixels + " "
                    + displayMetrics.scaledDensity + " " + displayMetrics.ydpi + " y inches: " + displayMetrics.heightPixels/displayMetrics.ydpi
                    + " " + it)
        }
        //paintedPixels.layoutParams = lp

        val fb: FloatingActionButton = findViewById<FloatingActionButton>(R.id.edit)

        Log.d(TAG, displayMetrics.toString())
    }
}