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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.afroditexekoukoulotaki.R
import com.afroditexekoukoulotaki.data.repository.impl.PaintPixelsRepositoryImpl
import com.afroditexekoukoulotaki.databinding.FragmentStartViewBinding
import com.afroditexekoukoulotaki.presentation.viewmodels.HairSharedViewModel
import kotlinx.coroutines.MainScope
import java.time.Year
import java.util.Calendar


class StartViewFragment : Fragment() {

    private lateinit var binding: FragmentStartViewBinding
    private val viewModel: HairSharedViewModel by activityViewModels()
    // Afro do I need this?
    private val repository = PaintPixelsRepositoryImpl()
    private lateinit var displayMetrics: DisplayMetrics
    // Afro why companion?
    companion object {
        private const val TAG: String = "StartView"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStartViewBinding.inflate(inflater, container, false)

        displayMetrics = resources.displayMetrics
        var currentDate = Calendar.getInstance()
        var numberOfDays: Int = 0 // to fix that
        viewModel.cutDate.observe(viewLifecycleOwner) {
            Log.d( "TAG", "observing cutDate... $it")
            numberOfDays = viewModel.daysDifference(currentDate, it).toInt()
            Log.d( "TAG", "numberOfDays: $numberOfDays")
            paintPixels(displayMetrics, numberOfDays)
        }
        //Log.d( "TAG", "numberOfDays after cutdate observe: $numberOfDays")


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
        Log.d( "TAG", "paintPixels -> numberOfDays: $numberOfDays")
        viewModel.pixelsToPaint(displayMetrics, numberOfDays)
        viewModel.numPixels.observe(viewLifecycleOwner) {
            Log.d( "TAG", "2. observe numPixels: $it")
            layoutParams.height = it

            // I dont kno what Im doin
            MainScope().launch(Dispatchers.Main) {
                view?.invalidate()
                Log.d( "TAG", "3. invalidate")
            }

            /*Log.d( TAG, "" + displayMetrics.density + " " + displayMetrics.densityDpi + " "
                    + displayMetrics.heightPixels + " " + displayMetrics.scaledDensity + " "
                    + displayMetrics.ydpi + " y inches: " + displayMetrics.heightPixels / displayMetrics.ydpi + " " + it)*/
        }

    }

    override fun onResume() {
        super.onResume()

    }

}