package com.dustanmbaga.pps.ui.acaricides.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AcaricideDetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Acaricides Details Fragment"
    }
    val text: LiveData<String> = _text
}