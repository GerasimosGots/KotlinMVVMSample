package com.gerasimosGk.kotlinmvvmsample.presentation.feature

import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import com.gerasimosGk.kotlinmvvmsample.R
import com.gerasimosGk.kotlinmvvmsample.data.ErrorDataModel
import com.gerasimosGk.kotlinmvvmsample.databinding.ActivityUserBinding
import com.gerasimosGk.kotlinmvvmsample.presentation.GlobalEvents
import com.gerasimosGk.kotlinmvvmsample.presentation.base.BaseActivity
import com.gerasimosGk.kotlinmvvmsample.presentation.base.activityViewModelBuilder
import com.gerasimosGk.kotlinmvvmsample.presentation.showAlertDialog
import com.gerasimosGk.kotlinmvvmsample.presentation.visible

class UserActivity : BaseActivity<ActivityUserBinding>() {

    private val sharedViewModel: UserSharedVM by activityViewModelBuilder {
        UserSharedVM()
    }

    override fun getActivityBinding(inflater: LayoutInflater) = ActivityUserBinding.inflate(layoutInflater)

    override fun onViewCreated() {}

    override fun getNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
    }

    override fun setObservers() {
        sharedViewModel.globalViewEvents.observe(this) {
            when (it) {
                is GlobalEvents.OnShowProgressBar -> {
                    binding?.progressBarContainer?.visible(it.isVisible)
                }
                is GlobalEvents.OnShowErrorMessage -> {
                    showErrorMessage(it.error)
                }
            }
        }
    }

    private fun showErrorMessage(error: ErrorDataModel) {
        showAlertDialog(
            title = getString(R.string.error_dialog_generic_title),
            message = error.errorMessage ?: getString(R.string.error_communication_msg),
            positiveButtonText = getString(R.string.OK),
            onPositiveClickListener = { _, _ ->
                // Do Nothing
            }
        )
    }
}