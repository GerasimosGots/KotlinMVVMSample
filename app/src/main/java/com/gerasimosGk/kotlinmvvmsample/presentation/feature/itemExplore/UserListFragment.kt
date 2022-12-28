package com.gerasimosGk.kotlinmvvmsample.presentation.feature.itemExplore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gerasimosGk.kotlinmvvmsample.R
import com.gerasimosGk.kotlinmvvmsample.data.model.domain.CustomToolbarModel
import com.gerasimosGk.kotlinmvvmsample.data.model.domain.Resource
import com.gerasimosGk.kotlinmvvmsample.data.model.domain.collect
import com.gerasimosGk.kotlinmvvmsample.databinding.FragmentUserListBinding
import com.gerasimosGk.kotlinmvvmsample.domain.UserUseCase
import com.gerasimosGk.kotlinmvvmsample.presentation.base.BaseFragment
import com.gerasimosGk.kotlinmvvmsample.presentation.base.viewModelBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : BaseFragment<UserListFragmentVM, FragmentUserListBinding>(),
    UserListAdapter.AdapterListener {

    @Inject
    lateinit var userUseCase: UserUseCase

    override val viewModel by viewModelBuilder {
        UserListFragmentVM(userUseCase = userUseCase)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentUserListBinding.inflate(inflater, container, false)

    // Lazy initialization of the UserListAdapter
    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter()
    }


    override fun initView() {
        viewModel.requestData()

        // View related initializations
        initAdapter()
    }

    override fun setObservers() {
        viewModel.userListState.observe(viewLifecycleOwner) { result ->
            sharedViewModel.updateLoadingState(result is Resource.Loading)
            result.collect(
                onSuccess = { list ->
                    list?.let {
                        userListAdapter.set(it)
                    }
                },
                onError = {
                    sharedViewModel.emitError(it)
                })
        }
    }

    override fun onCardListClicked(id: String) {
        viewModel.onUserSelected(id = id)

        // Navigate to next
        navController.navigate(R.id.action_UserList_to_UserDetails)
    }

    override fun setToolbar() {
        val model = CustomToolbarModel(R.string.user_list_title, false)
        binding?.toolbarView?.setView(model)
    }

    private fun initAdapter() {
        binding?.userRecyclerView?.also {
            it.adapter = userListAdapter
            it.layoutManager = LinearLayoutManager(it.context, LinearLayoutManager.VERTICAL, false)
        }
        userListAdapter.setListener(WeakReference(this))
    }
}