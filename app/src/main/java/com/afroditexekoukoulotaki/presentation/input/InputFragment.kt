package com.afroditexekoukoulotaki.presentation.input


import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.afroditexekoukoulotaki.databinding.FragmentInputBinding
import com.afroditexekoukoulotaki.presentation.viewmodels.HairSharedViewModel
import java.util.*

class InputFragment : Fragment() {

    private val viewModel: HairSharedViewModel by activityViewModels()
    private lateinit var _binding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        //setupObservers()
        setupUI()
        return _binding.root
    }

    /**
     * When on click datePickerButton should DatePickerDialog pop up
     * DatePickerDialog does not pop up! Maybe because is fragment not activity
     * reference tutorial https://www.youtube.com/watch?v=w038N6FWYOc
     */
    private fun setupUI() {
        _binding.datePickerButton.setOnClickListener {
            showDateDialog()
        }


/*        _binding.button.setOnClickListener {
            viewModel.saveDate(_binding.editTextDate.text.toString()) // post
        }*/
    }

    /**
     * Be careful when you build widgets programmatically, first create
     * the instance and after that, you can add all the listeners and
     * everything.
     */
    private fun showDateDialog() {
        val aCalendar: Calendar = Calendar.getInstance()
        val datePicker =
            DatePickerDialog(requireContext())
        datePicker.setOnDateSetListener { _, year, month, dayOfMonth ->
            aCalendar.set(year, month, dayOfMonth)
            viewModel.setCutDate(aCalendar)
            datePicker.dismiss()
        }
        datePicker.show()
    }

    private fun setupObservers() {
        viewModel.cutDay.observe(viewLifecycleOwner) { cutDay ->
            // afro i dont need that
            _binding.editTextDate.setText(cutDay)
        }
    }

}