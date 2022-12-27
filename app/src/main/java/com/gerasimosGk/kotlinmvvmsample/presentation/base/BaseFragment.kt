package com.gerasimosGk.kotlinmvvmsample.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.gerasimosGk.kotlinmvvmsample.presentation.feature.UserSharedVM

abstract class BaseFragment<VM : ViewModel, B : ViewBinding> : Fragment() {

    protected val sharedViewModel : UserSharedVM by activityViewModelBuilder {
        UserSharedVM()
    }

    protected abstract val viewModel: VM

    protected var binding: B? = null

    protected abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    protected abstract fun initView()

    protected abstract fun setObservers()

    // Nav controller, lazy init, Usage for all fragment children, for the navigation
    protected val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObservers()
    }

    // Fragments outlive their views. We clean up any references to the binding class instance.
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}