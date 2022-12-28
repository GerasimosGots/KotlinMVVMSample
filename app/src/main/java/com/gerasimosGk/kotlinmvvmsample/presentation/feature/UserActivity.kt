package com.gerasimosGk.kotlinmvvmsample.presentation.feature

import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import com.gerasimosGk.kotlinmvvmsample.R
import com.gerasimosGk.kotlinmvvmsample.data.model.error.ErrorDataModel
import com.gerasimosGk.kotlinmvvmsample.databinding.ActivityUserBinding
import com.gerasimosGk.kotlinmvvmsample.presentation.UIGlobalEvents
import com.gerasimosGk.kotlinmvvmsample.presentation.base.BaseActivity
import com.gerasimosGk.kotlinmvvmsample.presentation.base.activityViewModelBuilder
import com.gerasimosGk.kotlinmvvmsample.presentation.showAlertDialog
import com.gerasimosGk.kotlinmvvmsample.presentation.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : BaseActivity<ActivityUserBinding>() {

    private val sharedViewModel: UserSharedVM by activityViewModelBuilder {
        UserSharedVM()
    }

    override fun getActivityBinding(inflater: LayoutInflater) = ActivityUserBinding.inflate(layoutInflater)

    override fun onViewCreated() {}

    override fun getNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment_container) as NavHostFragment
    }

    override fun setObservers() {
        sharedViewModel.globalViewEvents.observe(this) {
            when (it) {
                is UIGlobalEvents.OnShowProgressBar -> {
                    binding?.progressBarContainer?.visible(it.isVisible)
                }
                is UIGlobalEvents.OnShowErrorMessage -> {
                    showErrorMessage(it.error)
                }
            }
        }
    }

    private fun showErrorMessage(error: ErrorDataModel) {
        showAlertDialog(
            title = getString(R.string.error_dialog_generic_title),
            message = getString(error.errorMessage),
            positiveButtonText = getString(R.string.OK),
            onPositiveClickListener = { _, _ ->
                // Do Nothing
            }
        )
    }
}