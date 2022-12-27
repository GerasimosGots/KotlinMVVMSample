package com.gerasimosGk.kotlinmvvmsample.data.network.service

import com.gerasimosGk.kotlinmvvmsample.data.ApiUserModel
import retrofit2.http.GET

/**
 * Created by Gerasimos on 25/11/2021
 */
interface UserService {
    @GET("/users")
    suspend fun getUsers(): MutableList<ApiUserModel>
}