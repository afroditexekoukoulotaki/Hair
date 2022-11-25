package com.afroditexekoukoulotaki.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.afroditexekoukoulotaki.data.repository.PaintPixelsRepository
import com.afroditexekoukoulotaki.data.repository.impl.PaintPixelsRepositoryImpl
import com.afroditexekoukoulotaki.databinding.ActivityMainBinding
import com.afroditexekoukoulotaki.presentation.viewmodels.HairSharedViewModel
import com.afroditexekoukoulotaki.presentation.viewmodels.ViewModelFactory

/**
 * In this project user will input the date she cut her hair and after save
 * she will track how long her hair grows every day.
 * Y axis of painted pixels represents the real life length of her hair
 * has grown from the start of the haircut date.
 *
 * The first time she opens the app cutDate = currentDate
 * MainActivity and InputFragment share the HairSharedViewModel
 * When FloatingActionButton is pressed, InputFragment is created
 *
 * Please help me with navigation,
 * how to open the fragment
 * where to initialize cutDate as currentDate
 * how to save editTextDate as variable
 * xml needs improvement
 */


/**
 * I made a few changes in your code, first, you need to create at Activity level your shared view model
 * with this you have access across all the app using activityViewModels in your fragments.
 *
 * Take a look into activity_main, i made a change there too.
 * Take a look into your navigation XML
 *
 * I organized your folders because this was a mess. but i understand. BUT try to keep your code in order
 * this will make your life easier
 *
 * the repository lives into data layer, so i moved there too, if you have a repo, always make an interface
 * because if you make changes into the implementation you will notice some errors, with the interface
 * you have an idea how to implement the functions that you really need, INTERFACE SEGREGATION PRINCIPLE
 *
 * LOV U A LOT :)
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HairSharedViewModel
    private val repository: PaintPixelsRepository = PaintPixelsRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(repository)
        )[HairSharedViewModel::class.java]
        setContentView(binding.root)
    }

}