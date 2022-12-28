package com.gerasimosGk.kotlinmvvmsample.data.network.service

import com.gerasimosGk.kotlinmvvmsample.data.model.api.UserModelResponse
import com.gerasimosGk.kotlinmvvmsample.data.model.api.DataResource
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Gerasimos on 25/11/2021
 */
interface UserService {
    @GET("/users")
    suspend fun getUsers(): Response<MutableList<UserModelResponse>>
}