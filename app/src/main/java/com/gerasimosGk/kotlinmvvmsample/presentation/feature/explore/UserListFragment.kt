package com.gerasimosGk.kotlinmvvmsample.presentation.feature.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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
        //TODO
        //presenter?.requestData()
        // View related initializations
        initAdapter()
    }

    override fun setObservers() {
        TODO("Not yet implemented")
    }

    private fun initAdapter() {
        binding?.userRecyclerView?.also {
            it.adapter = userListAdapter
            it.layoutManager = LinearLayoutManager(it.context, LinearLayoutManager.VERTICAL, false)
        }
        userListAdapter.setListener(WeakReference(this))
    }

    override fun onCardListClicked(id: String) {
        TODO("Not yet implemented")
    }
}