package com.gerasimosGk.kotlinmvvmsample.data.repositoty

import com.gerasimosGk.kotlinmvvmsample.data.ApiUserModel
import com.gerasimosGk.kotlinmvvmsample.data.DataResource

interface UserRepository {
    suspend fun getUserList(): DataResource<MutableList<ApiUserModel>>

    fun getPhotoById(id: String): String
}