package com.afroditexekoukoulotaki.presentation.input

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.afroditexekoukoulotaki.presentation.viewmodels.HairSharedViewModel
import com.afroditexekoukoulotaki.databinding.FragmentInputBinding
import java.time.Year
import java.util.Calendar

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
     * We post value to date when on click or when the input changes?
     */
    private fun setupUI() {


        _binding.datePickerB.setOnClickListener {
            val aCalendar: Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                aCalendar.set(year, month, dayOfMonth)
            }
            context?.let { it1 ->
                DatePickerDialog(
                    it1, datePicker, aCalendar.get(Calendar.YEAR),
                    aCalendar.get(Calendar.MONTH), aCalendar.get(Calendar.DAY_OF_MONTH))
            }
            Toast.makeText(activity, "test", Toast.LENGTH_LONG).show()
            viewModel.setCutDate(aCalendar)
        }


/*        _binding.button.setOnClickListener {
            viewModel.saveDate(_binding.editTextDate.text.toString()) // post
        }*/
    }

    private fun setupObservers() {
        viewModel.cutDay.observe(viewLifecycleOwner) { cutDay ->
            // afro calculate number of days with cutDay and current date
            _binding.editTextDate.setText(cutDay)
        }
    }

}