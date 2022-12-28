package com.gerasimosGk.kotlinmvvmsample.presentation.feature.itemDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerasimosGk.kotlinmvvmsample.data.model.api.DataResource
import com.gerasimosGk.kotlinmvvmsample.data.model.domain.Resource
import com.gerasimosGk.kotlinmvvmsample.domain.UserModel
import com.gerasimosGk.kotlinmvvmsample.domain.UserUseCase
import kotlinx.coroutines.launch

class UserDetailsFragmentVM constructor(private val userUseCase: UserUseCase) : ViewModel() {

    val userState: MutableLiveData<Resource<UserModel>> by lazy {
        MutableLiveData()
    }

    fun requestSelectedUser() {
        viewModelScope.launch {

            userState.value = Resource.loading()

            val result: DataResource<UserModel> = userUseCase.getSelectedUser()

            when (result) {
                is DataResource.Success -> {
                    userState.value = Resource.success(result.value)
                }

                is DataResource.Error -> {
                    userState.value = Resource.error(result.errorData)
                }
            }
        }
    }
}