package com.gerasimosGk.kotlinmvvmsample.presentation.feature.details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.gerasimosGk.kotlinmvvmsample.R
import com.gerasimosGk.kotlinmvvmsample.databinding.FragmentUserDetailsBinding
import com.gerasimosGk.kotlinmvvmsample.presentation.base.BaseFragment
import com.gerasimosGk.kotlinmvvmsample.presentation.base.viewModelBuilder
import com.gerasimosGk.kotlinmvvmsample.presentation.views.CustomToolbar
import java.lang.ref.WeakReference

class UserDetailsFragment : BaseFragment<UserDetailsFragmentVM, FragmentUserDetailsBinding>(), CustomToolbar.BackButtonListener {

    override val viewModel by viewModelBuilder {
        UserDetailsFragmentVM()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false)


    override fun initView() {
        binding?.toolbarView?.setListener(WeakReference(this))

        binding?.ctaButtonView?.setOnClickListener {
            context?.let {
                Toast.makeText(it, R.string.toast_text, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun setObservers() {
        TODO("Not yet implemented")
    }

    override fun onBackButtonCLickListener() {
        navController.navigateUp()
    }
}