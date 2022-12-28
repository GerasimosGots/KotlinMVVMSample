package com.gerasimosGk.kotlinmvvmsample.domain

import com.gerasimosGk.kotlinmvvmsample.R
import com.gerasimosGk.kotlinmvvmsample.data.model.api.DataResource
import com.gerasimosGk.kotlinmvvmsample.data.model.api.UserModelResponse
import com.gerasimosGk.kotlinmvvmsample.data.model.error.ErrorDataModel
import com.gerasimosGk.kotlinmvvmsample.data.model.error.ErrorType
import com.gerasimosGk.kotlinmvvmsample.data.repositoty.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    UserUseCase {

    private val cachedUserModelList: MutableList<UserModel> = mutableListOf()
    private var selectedUserId: String? = null

    override suspend fun getUserList(): DataResource<MutableList<UserModel>> {
        clearCache()

        val result = userRepository.getUserList()

         when (result) {
            is DataResource.Success -> {
                val userList = result.value.body()

                if (userList == null) {
                    val errorDataModel = ErrorDataModel(
                        errorType = ErrorType.CustomError,
                        errorMessage = R.string.generic_error_message
                    )
                    return DataResource.Error(errorDataModel)
                }

                val mappedList =  userList.map { apiUserModel ->
                            val photoUrl = userRepository.getPhotoById(apiUserModel.id)
                            val userModel = apiUserModel.toUserModel(photoUrl = photoUrl)
                           userModel
                        }.toMutableList().also {
                            addToCache(it)
                            DataResource.success(it)
                        }

                return DataResource.success(mappedList)
            }

            is DataResource.Error ->  {
                val errorDataModel = ErrorDataModel(
                    errorType = ErrorType.CustomError,
                    errorMessage = R.string.generic_error_message
                )
                return DataResource.Error(errorDataModel)
            }
        }
    }

    override fun selectedUser(id: String) {
        selectedUserId = id
    }

    override suspend fun getSelectedUser(): DataResource<UserModel> {
        if (cachedUserModelList.isEmpty()) {
            val errorDataModel = ErrorDataModel(
                errorType = ErrorType.CustomError,
                errorMessage = R.string.generic_error_message
            )
            return DataResource.Error(errorDataModel)
        }

        val resultList = cachedUserModelList.filter { it.id == selectedUserId }
        return if (resultList.isEmpty()) {
            val errorDataModel = ErrorDataModel(
                errorType = ErrorType.CustomError,
                errorMessage = R.string.generic_error_message
            )
            return DataResource.Error(errorDataModel)
        } else {
            DataResource.success(resultList[0])
        }
    }

    private fun addToCache(userModelList: MutableList<UserModel>?) {
        if (userModelList == null) {
            return
        }

        cachedUserModelList.addAll(userModelList)
    }

    private fun clearCache() {
        cachedUserModelList.clear()
        selectedUserId = null
    }
}