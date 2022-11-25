package com.afroditexekoukoulotaki.presentation.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.afroditexekoukoulotaki.presentation.viewmodels.HairSharedViewModel
import com.afroditexekoukoulotaki.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private val viewModel: HairSharedViewModel by activityViewModels()
    private lateinit var _binding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        setupObservers()
        setupUI()
        return _binding.root
    }

    private fun setupUI() {
        _binding.button.setOnClickListener {
            viewModel.saveDate(_binding.editTextDate.text.toString())
        }
    }

    private fun setupObservers() {
        viewModel.cutDay.observe(viewLifecycleOwner) { cutDay ->
            _binding.editTextDate.setText(cutDay)
        }
    }

}