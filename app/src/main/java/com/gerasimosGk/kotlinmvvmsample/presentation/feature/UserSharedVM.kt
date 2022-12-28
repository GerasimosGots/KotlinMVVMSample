package com.gerasimosGk.kotlinmvvmsample.presentation.feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gerasimosGk.kotlinmvvmsample.data.model.error.ErrorDataModel
import com.gerasimosGk.kotlinmvvmsample.presentation.UIGlobalEvents

/**
 * The usage of a shared ViewModel object is solely for the communication of some basic states / events
 * of a flow.
 * In the architecture of a single activity and multiple fragment, that can be traslated as a specific flow
 * e.g a Login flow.
 * So the SharedVM is this case is responsible for the single point of truth and communication of some
 * ui states and events across the flow
 */
class UserSharedVM : ViewModel() {
    val globalViewEvents: MutableLiveData<UIGlobalEvents> by lazy {
        MutableLiveData()
    }

    fun updateLoadingState(isLoading: Boolean = false) {
        globalViewEvents.value = UIGlobalEvents.OnShowProgressBar(isLoading)
    }

    fun emitError(error: ErrorDataModel) {
        globalViewEvents.value = UIGlobalEvents.OnShowErrorMessage(error)
    }
}