package com.afroditexekoukoulotaki.presentation.input


import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
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
     * When on click datePickerButton should DatePickerDialog pop up
     * DatePickerDialog does not pop up! Maybe because is fragment not activity
     *
     */
    private fun setupUI() {
        _binding.datePickerButton.setOnClickListener {
            val aCalendar: Calendar = Calendar.getInstance()
            Toast.makeText(activity, "" + aCalendar.get(Calendar.YEAR), Toast.LENGTH_SHORT).show()
            val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                aCalendar.set(year, month, dayOfMonth)
            }
            context?.let { it1 ->
                DatePickerDialog(
                    it1, datePicker, aCalendar.get(Calendar.YEAR),
                    aCalendar.get(Calendar.MONTH), aCalendar.get(Calendar.DAY_OF_MONTH))
            }
            //Toast.makeText(activity, "test", Toast.LENGTH_LONG).show()
            Toast.makeText(activity, "after" + aCalendar.get(Calendar.YEAR), Toast.LENGTH_SHORT).show()
            viewModel.setCutDate(aCalendar) // postValue

        }


/*        _binding.button.setOnClickListener {
            viewModel.saveDate(_binding.editTextDate.text.toString()) // post
        }*/
    }

    private fun setupObservers() {
        viewModel.cutDay.observe(viewLifecycleOwner) { cutDay ->
            // afro i dont need that
            _binding.editTextDate.setText(cutDay)
        }
    }

}