package com.example.phonetreemanager.ui.call_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CallInfoModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Call Info Fragment"
    }
    val text: LiveData<String> = _text
}