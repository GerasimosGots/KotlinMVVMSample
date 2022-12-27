package com.gerasimosGk.kotlinmvvmsample.presentation.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gerasimosGk.kotlinmvvmsample.data.ErrorDataModel
import com.gerasimosGk.kotlinmvvmsample.presentation.GlobalEvents

class UserSharedVM : ViewModel() {
    private val _globalViewEvents: MutableLiveData<GlobalEvents> = MutableLiveData()
    val globalViewEvents: LiveData<GlobalEvents>
        get() = _globalViewEvents


    fun updateLoadingState(isLoading: Boolean = false) {
        _globalViewEvents.value = GlobalEvents.OnShowProgressBar(isLoading)
    }

    fun emitError(error: ErrorDataModel) {
        _globalViewEvents.value = GlobalEvents.OnShowErrorMessage(error)
    }
}