package com.gerasimosGk.kotlinmvvmsample.data.repositoty

import com.gerasimosGk.kotlinmvvmsample.data.model.api.UserModelResponse
import com.gerasimosGk.kotlinmvvmsample.data.model.api.DataResource
import retrofit2.Response

interface UserRepository {
    suspend fun getUserList(): DataResource<Response<MutableList<UserModelResponse>>>

    fun getPhotoById(id: String): String
}