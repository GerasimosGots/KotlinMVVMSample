package com.gerasimosGk.kotlinmvvmsample.presentation.feature.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerasimosGk.kotlinmvvmsample.data.DataResource
import com.gerasimosGk.kotlinmvvmsample.data.Resource
import com.gerasimosGk.kotlinmvvmsample.domain.UserModel
import com.gerasimosGk.kotlinmvvmsample.domain.UserUseCase
import kotlinx.coroutines.launch

class UserDetailsFragmentVM constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _userState: LiveData<Resource<UserModel>> = MutableLiveData()
    val userState: LiveData<Resource<UserModel>>
        get() = _userState

    fun requestSelectedUser() {
        viewModelScope.launch {

            _userState.value = Resource.loading()

            val result: DataResource<UserModel> = userUseCase.getSelectedUser()

            when (result) {
                is DataResource.Success -> {
                    _userState.value = Resource.success(result.value)
                }

                is DataResource.Error -> {
                    _userState.value = Resource.error(result.errorData)
                }
            }
        }
    }
}