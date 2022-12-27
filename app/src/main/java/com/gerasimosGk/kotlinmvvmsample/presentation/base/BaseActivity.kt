package com.gerasimosGk.kotlinmvvmsample.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.gerasimosGk.kotlinmvvmsample.R

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    var binding: B? = null

    abstract fun getActivityBinding(inflater: LayoutInflater): B

    abstract fun onViewCreated()

    abstract fun setObservers()

    abstract fun getNavHostFragment(): NavHostFragment

    protected val navController by lazy {
        getNavHostFragment().navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_KotlinMvvmSample)
        super.onCreate(savedInstanceState)

        binding = getActivityBinding(layoutInflater)
        setContentView(binding?.root)
        onViewCreated()
        setObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}