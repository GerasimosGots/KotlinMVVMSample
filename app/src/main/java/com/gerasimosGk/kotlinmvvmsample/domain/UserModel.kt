package com.gerasimosGk.kotlinmvvmsample.domain

/**
 * Created by Gerasimos on 6/12/2021
 *
 * Domain  data class Model, it's the model tha we use in the Domain layer and Presentation layer
 */
data class UserModel(
    val id: String = "",
    val userName: String = "",
    val email: String = "",
    val phone: String = "",
    val photoUrl : String = ""
)