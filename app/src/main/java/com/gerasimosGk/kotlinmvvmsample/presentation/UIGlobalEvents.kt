package com.gerasimosGk.kotlinmvvmsample.presentation

import com.gerasimosGk.kotlinmvvmsample.data.model.error.ErrorDataModel

sealed class UIGlobalEvents {
    data class OnShowProgressBar(
        val isVisible: Boolean
    ) : UIGlobalEvents()

    data class OnShowErrorMessage(
        val error: ErrorDataModel
    ) : UIGlobalEvents()
}