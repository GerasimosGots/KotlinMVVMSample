package com.gerasimosGk.kotlinmvvmsample.domain

import com.gerasimosGk.kotlinmvvmsample.data.ApiUserModel

fun ApiUserModel.toUserModel(photoUrl: String = ""): UserModel {
    return UserModel(
        id = this.id,
        email = this.email,
        userName = this.userName,
        phone = this.phone,
        photoUrl = photoUrl
    )
}