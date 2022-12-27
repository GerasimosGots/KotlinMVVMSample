package com.gerasimosGk.kotlinmvvmsample.presentation.feature.explore

/**
 * Created by Gerasimos on 6/12/2021
 *
 * Ui data class Model, it's the model used only in Presentation layer the adapter to show the user list
 */
data class UIUserListModel(var id: String,
                           var description: String,
                           var coverImage: String)
