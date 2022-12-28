package com.gerasimosGk.kotlinmvvmsample.presentation.feature.itemExplore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerasimosGk.kotlinmvvmsample.data.model.api.DataResource
import com.gerasimosGk.kotlinmvvmsample.data.model.api.toSuccessResource
import com.gerasimosGk.kotlinmvvmsample.data.model.domain.Resource
import com.gerasimosGk.kotlinmvvmsample.domain.UserModel
import com.gerasimosGk.kotlinmvvmsample.domain.UserUseCase
import kotlinx.coroutines.launch

class UserListFragmentVM constructor(private val userUseCase: UserUseCase) : ViewModel() {

    val userListState: MutableLiveData<Resource<MutableList<UIUserListModel>>> by lazy {
        MutableLiveData()
    }

    /**
     * View requested Data
     *
     * To fetch the requested data we use the UserUseCase (that is injected in class's constructor).
     * The UseCase in responsible for fetching a stream of mapped data that then we observe
     * and return to the view with usage of live data.
     */
    fun requestData() {
        viewModelScope.launch {
            userListState.value = Resource.loading()

            val result: DataResource<MutableList<UserModel>> = userUseCase.getUserList()

            when (result) {
                is DataResource.Success -> {
                    userListState.value = result.value.toUserListModel().toSuccessResource()
                }

                is DataResource.Error -> {
                    userListState.value = Resource.error(result.errorData)
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