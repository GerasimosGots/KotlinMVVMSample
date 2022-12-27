package com.gerasimosGk.kotlinmvvmsample.data.repositoty

import com.gerasimosGk.kotlinmvvmsample.data.ApiUserModel
import com.gerasimosGk.kotlinmvvmsample.data.DataResource
import com.gerasimosGk.kotlinmvvmsample.data.network.ApiClient
import com.gerasimosGk.kotlinmvvmsample.data.network.performApiCall
import com.gerasimosGk.kotlinmvvmsample.data.network.service.UserService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userService: UserService
) : UserRepository {

    override suspend fun getUserList(): DataResource<MutableList<ApiUserModel>> {
        return performApiCall(dispatcher = dispatcher){
            userService.getUsers()
        }
    }

    override fun getPhotoById(id: String): String {
        return getMockImagesById(photoId = id)

    }

    private fun getMockImagesById(photoId: String): String {
        val photoUrl = ApiClient.SECONDARY_URL
            .plus(photoId)
            .plus("/800/900")
        return photoUrl
    }
}