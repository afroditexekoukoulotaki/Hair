package com.afroditexekoukoulotaki.presentation.viewmodels

import android.util.DisplayMetrics
import android.util.Log
import androidx.lifecycle.*
import com.afroditexekoukoulotaki.data.repository.PaintPixelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

class HairSharedViewModel(
    private val repository: PaintPixelsRepository
): ViewModel() {
    //if you have a MutableLiveData, you actually can initiate with an empty MutableLiveData()
    private val _currentDate: MutableLiveData<Date> = MutableLiveData() // maybe not mutable
    val currentDate: LiveData<Date>
        get() = Transformations.map(_currentDate) { it }

    private val numPixelsM = MutableLiveData<Int>()
    val numPixels: LiveData<Int>
        get() = Transformations.map(numPixelsM) { it.toInt() }

    private val numberOfDaysM = MutableLiveData<Int>() // maybe not mutable
    val numberOfDays: LiveData<Int>
        get() = Transformations.map(numberOfDaysM) { it.toInt() }

    //lateinit var cutDay: MonthDay
    private val _cutDay: MutableLiveData<String> = MutableLiveData()
    val cutDay: LiveData<String>
        get() = Transformations.map(_cutDay) { it }

    //val calendar: Calendar = Calendar.getInstance()
    private val _cutDate: MutableLiveData<Calendar> = MutableLiveData()
    val cutDate: LiveData<Calendar>
        get() = _cutDate

    // how to deal with requires api level 26?
    //var today: MonthDay = MonthDay.now()

    val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    //val currentDate: String = simpleDate.format(Date())
    //println(" Current Date is: " +currentDate)

    private val TAG = "HairSharedViewModel"

    /**
     * We save the date the user had her hair cut.
     */
    fun saveDate(date: String){ // to delete
        _cutDay.postValue(date)
    }

    fun setCutDate(date: Calendar){
        _cutDate.postValue(date)
    }

    fun daysDifference(c1: Calendar, c2: Calendar): Long {
        val diffInMillis = c1.timeInMillis - c2.timeInMillis
        return diffInMillis.milliseconds.inWholeDays
    }

    fun pixelsToPaint(displayMetrics: DisplayMetrics, numberOfDays: Int) {
        // I don't know which Dispatcher should I use
        viewModelScope.launch(Dispatchers.Main) {
            numPixelsM.postValue(repository.pixelsToPaint(displayMetrics, numberOfDays))
            Log.d(TAG, "pixelsToPaint(displayMetrics) " + repository.pixelsToPaint(displayMetrics, numberOfDays) + "")
        }
    }
}

class ViewModelFactory(private val repository: PaintPixelsRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    //This functiion is an override of factory creation of the extended interface Factory,
    //you need to return de viewmodel as a generic T to give the instance to your activity
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HairSharedViewModel::class.java)) {
            return HairSharedViewModel(repository) as T
        } else {
            throw java.lang.NullPointerException("Class not found")
        }

    }
}