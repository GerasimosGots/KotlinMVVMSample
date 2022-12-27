package com.gerasimosGk.kotlinmvvmsample.presentation.feature.details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.gerasimosGk.kotlinmvvmsample.R
import com.gerasimosGk.kotlinmvvmsample.data.Resource
import com.gerasimosGk.kotlinmvvmsample.data.collect
import com.gerasimosGk.kotlinmvvmsample.databinding.FragmentUserDetailsBinding
import com.gerasimosGk.kotlinmvvmsample.domain.UserModel
import com.gerasimosGk.kotlinmvvmsample.domain.UserUseCase
import com.gerasimosGk.kotlinmvvmsample.presentation.base.BaseFragment
import com.gerasimosGk.kotlinmvvmsample.presentation.base.viewModelBuilder
import com.gerasimosGk.kotlinmvvmsample.presentation.loadImageWithBottomCorners
import com.gerasimosGk.kotlinmvvmsample.presentation.views.CustomToolbar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment<UserDetailsFragmentVM, FragmentUserDetailsBinding>(), CustomToolbar.BackButtonListener {

    @Inject
    lateinit var userUseCase: UserUseCase

    override val viewModel by viewModelBuilder {
        UserDetailsFragmentVM(userUseCase = userUseCase)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false)


    override fun initView() {
        viewModel.requestSelectedUser()

        binding?.toolbarView?.setListener(WeakReference(this))

        binding?.ctaButtonView?.setOnClickListener {
            context?.let {
                Toast.makeText(it, R.string.toast_text, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun setObservers() {
        viewModel.userState.observe(viewLifecycleOwner) { result ->
            sharedViewModel.updateLoadingState(result is Resource.Loading)
            result.collect(
                onSuccess = { model ->
                    model?.let {
                        setView(userModel = it)
                    }
                },
                onError = {
                    sharedViewModel.emitError(it)
                })
        }
    }

    override fun onBackButtonCLickListener() {
        navController.navigateUp()
    }

    private fun setView(userModel: UserModel) {
        binding?.apply {
            coverImageView.loadImageWithBottomCorners(userModel.photoUrl)
            userNameTextView.text = userModel.userName
            emailTextView.text = userModel.email
            phoneTextView.text = userModel.phone
            ctaButtonView.text = String.format(getString(R.string.cta_text), userModel.userName)
        }
    }
}