package com.dustanmbaga.pps.ui.acaricides

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AcaricidesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Acaricides Fragment"
    }
    val text: LiveData<String> = _text
}