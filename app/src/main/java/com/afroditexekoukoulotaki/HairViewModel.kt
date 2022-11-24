package com.afroditexekoukoulotaki

import android.util.DisplayMetrics
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HairViewModel(
    private val repository: Repository
): ViewModel() {

    private val _currentDate: MutableLiveData<Date>() // maybe not mutable
    val currentDate: LiveData<Date>
        get() = Transformations.map(_currentDate) { it }

    private val numPixelsM = MutableLiveData<Int>()
    val numPixels: LiveData<Int>
        get() = Transformations.map(numPixelsM) { it.toInt() }

    private val numberOfDaysM = MutableLiveData<Int>() // maybe not mutable
    val numberOfDays: LiveData<Int>
        get() = Transformations.map(numberOfDaysM) { it.toInt() }

    //lateinit var cutDay: MonthDay
    private val _cutDay: MutableLiveData<Date>()
    val cutDay: LiveData<Date>
        get() = Transformations.map(_cutDay) { it }

    // how to deal with requires api level 26?
    //var today: MonthDay = MonthDay.now()

    val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    //val currentDate: String = simpleDate.format(Date())
    //println(" Current Date is: " +currentDate)

    private val TAG = "HairViewModel"

    /**
     * We save the date the user had her hair cut.
     */
    fun saveDate(date: Date){
        cutDay.value = date
    }

    fun pixelsToPaint(displayMetrics: DisplayMetrics) {
        // I don't know which Dispatcher should I use
        viewModelScope.launch(Dispatchers.Main) {
            numPixelsM.postValue(repository.pixelsToPaint(displayMetrics))
            Log.d(TAG, "pixelsToPaint(displayMetrics) " + repository.pixelsToPaint(displayMetrics) + "")
        }
    }
}

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")

    //This functiion is an override of factory creation of the extended interface Factory,
    //you need to return de viewmodel as a generic T to give the instance to your activity
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HairViewModel::class.java)) {
            return HairViewModel(repository) as T
        } else {
            throw java.lang.NullPointerException("Class not found")
        }

    }
}