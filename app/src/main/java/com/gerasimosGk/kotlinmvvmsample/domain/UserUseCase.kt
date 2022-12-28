package com.gerasimosGk.kotlinmvvmsample.domain

import com.gerasimosGk.kotlinmvvmsample.data.model.api.DataResource

interface UserUseCase {
    suspend fun getUserList(): DataResource<MutableList<UserModel>>

    fun selectedUser(id: String)

    suspend fun getSelectedUser() : DataResource<UserModel>
}