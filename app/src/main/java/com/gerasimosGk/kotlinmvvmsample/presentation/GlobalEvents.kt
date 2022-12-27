package com.gerasimosGk.kotlinmvvmsample.presentation

import com.gerasimosGk.kotlinmvvmsample.data.ErrorDataModel

sealed class GlobalEvents {
    data class OnShowProgressBar(
        val isVisible: Boolean
    ) : GlobalEvents()

    data class OnShowErrorMessage(
        val error: ErrorDataModel
    ) : GlobalEvents()
}