package com.mpg.nagarro.deviceinventorymgmt.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val showMessage: MutableLiveData<String> = MutableLiveData()
}