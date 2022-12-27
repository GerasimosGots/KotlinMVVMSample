package com.gerasimosGk.kotlinmvvmsample.presentation.feature.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerasimosGk.kotlinmvvmsample.data.DataResource
import com.gerasimosGk.kotlinmvvmsample.data.Resource
import com.gerasimosGk.kotlinmvvmsample.domain.UserModel
import com.gerasimosGk.kotlinmvvmsample.domain.UserUseCase
import kotlinx.coroutines.launch

class UserListFragmentVM constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _userListState: LiveData<Resource<MutableList<UserModel>>> = MutableLiveData()
    val userListState: LiveData<Resource<MutableList<UserModel>>>
        get() = _userListState

    /**
     * View requested Data
     *
     * To fetch the requested data we use the UserUseCase (that is injected in class's constructor).
     * The UseCase in responsible for fetching a stream of mapped data that then we observe
     * and return to the view with usage of view interface.
     */
    fun requestData() {
        viewModelScope.launch {
            _userListState.value = Resource.loading()

            val result: DataResource<MutableList<UserModel>> = userUseCase.getUserList()

            when (result) {
                is DataResource.Success -> {
                    _userListState.value = Resource.success(result.value)
                }

                is DataResource.Error -> {
                    _userListState.value = Resource.error(result.errorData)
                }
            }
        }
    }

    /**
     * Set a selected user in th UseCase
     * @param id of type String
     */
    fun onUserSelected(id: String) {
        userUseCase.selectedUser(id = id)
    }
}