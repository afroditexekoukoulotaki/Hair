package com.afroditexekoukoulotaki.presentation.startView

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.afroditexekoukoulotaki.R
import com.afroditexekoukoulotaki.data.repository.impl.PaintPixelsRepositoryImpl
import com.afroditexekoukoulotaki.databinding.FragmentStartViewBinding
import com.afroditexekoukoulotaki.presentation.viewmodels.HairSharedViewModel
import java.time.Year
import java.util.Calendar


class StartViewFragment : Fragment() {

    private lateinit var binding: FragmentStartViewBinding
    private val viewModel: HairSharedViewModel by activityViewModels()
    private val repository = PaintPixelsRepositoryImpl()

    companion object {
        private const val TAG: String = "StartView"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStartViewBinding.inflate(inflater, container, false)

        var displayMetrics: DisplayMetrics = resources.displayMetrics

        viewModel.cutDate.observe(viewLifecycleOwner) {
            val currentDate = Calendar.getInstance()
            //val year: Int = currentDate.get(Calendar.YEAR) - it.get(Calendar.YEAR)
            //val month: Int = currentDate.get(Calendar.MONTH) - it.get(Calendar.MONTH)
            //val day: Int = currentDate.get(Calendar.DAY_OF_MONTH) - it.get(Calendar.DAY_OF_MONTH)
            var numberOfDays: Int = viewModel.daysDifference(currentDate, it).toInt()
            paintPixels(displayMetrics, numberOfDays)
        }




        //paintedPixels.layoutParams = lp
        setupUI()
        Log.d(TAG, displayMetrics.toString())
        return binding.root
    }

    private fun setupUI() {
        binding.fabEdit.setOnClickListener {
            this.findNavController().navigate(R.id.action_startViewFragment_to_input)
        }

    }

    // to change that
    private fun paintPixels(displayMetrics: DisplayMetrics, numberOfDays: Int) {
        //var displayMetrics: DisplayMetrics = resources.displayMetrics
        //paintedPixels.layoutParams = LayoutParams(100, 100)
        var layoutParams = binding.paintedPixels.layoutParams
        //lp.height = paintedPixelsY
        viewModel.pixelsToPaint(displayMetrics, numberOfDays)
        viewModel.numPixels.observe(viewLifecycleOwner) {
            layoutParams.height = it
            Log.d(
                TAG,
                "" + displayMetrics.density + " " + displayMetrics.densityDpi + " " + displayMetrics.heightPixels + " "
                        + displayMetrics.scaledDensity + " " + displayMetrics.ydpi + " y inches: " + displayMetrics.heightPixels / displayMetrics.ydpi
                        + " " + it
            )
        }

    }

}