package com.gerasimosGk.kotlinmvvmsample.data.model.domain

import androidx.annotation.StringRes

/**
 * Created by Gerasimos on 30/12/2021
 */
data class CustomToolbarModel(
    @StringRes val title: Int,
    val enableBackButton: Boolean
)