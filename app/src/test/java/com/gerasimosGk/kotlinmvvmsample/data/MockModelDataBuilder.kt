package com.gerasimosGk.kotlinmvvmsample.data

import com.gerasimosGk.kotlinmvvmsample.data.model.api.UserModelResponse
import com.gerasimosGk.kotlinmvvmsample.domain.UserModel

class MockModelDataBuilder {

    // Return a mock list with [ApiUserModel]
    fun createMockUserApiModelList () : MutableList<UserModelResponse>{
        val mockApiUserModelList : MutableList<UserModelResponse> = mutableListOf()

        // Create Mock users
        val apiUser1 = UserModelResponse(
            id = "0",
            userName = "name",
            email = "mail@mail.com",
            phone = "123456789"
        )

        val apiUser2 = UserModelResponse(
            id = "1",
            userName = "name2",
            email = "mail2@mail.com",
            phone = "123456789"
        )

        val apiUser3 = UserModelResponse(
            id = "2",
            userName = "name3",
            email = "mail3@mail.com",
            phone = "123456789"
        )

        // Add Mock user
        mockApiUserModelList.add(apiUser1)
        mockApiUserModelList.add(apiUser2)
        mockApiUserModelList.add(apiUser3)

        // Return list with Mock data
        return mockApiUserModelList
    }

    // Return a mock list with [UserModel]
    fun createMockUserModelList () : MutableList<UserModel>{
        val mockUserModelList : MutableList<UserModel> = mutableListOf()

        // Create Mock users
        val apiUser1 = UserModel(
            id = "0",
            userName = "name",
            email = "mail@mail.com",
            phone = "0446681800",
            photoUrl = "www.image/200/300"
        )

        val apiUser2 = UserModel(
            id = "1",
            userName = "name2",
            email = "wrongMail.com",
            phone = "wrong_phone",
            photoUrl = "www.image/200/300"
        )

        val apiUser3 = UserModel(
            id = "2",
            userName = "name3",
            email = "mail3@mail.com",
            phone = "123456789",
            photoUrl = "www.image/200/300"
        )

        // Add Mock user
        mockUserModelList.add(apiUser1)
        mockUserModelList.add(apiUser2)
        mockUserModelList.add(apiUser3)

        // Return list with Mock data
        return mockUserModelList
    }
}
