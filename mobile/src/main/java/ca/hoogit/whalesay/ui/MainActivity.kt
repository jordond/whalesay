package ca.hoogit.whalesay.ui

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ca.hoogit.coreview.activity.BindableActivity
import ca.hoogit.coreview.util.onDestinationChanged
import ca.hoogit.coreview.viewmodel.getViewModel
import ca.hoogit.whalesay.R
import ca.hoogit.whalesay.databinding.ActivityMainBinding

class MainActivity : BindableActivity<ActivityMainBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    private val viewModel by lazy { getViewModel<MainViewModel>() }

    override fun setupViews() {
        with(findNavController(R.id.navHost)) {
            setupToolbar(this)
            setupNavListener(this)
        }
    }

    private fun setupToolbar(navController: NavController) = with(binding.toolbar) {
        setSupportActionBar(this)
        setupWithNavController(navController)
    }

    private fun setupNavListener(navController: NavController) = with(navController) {
        onDestinationChanged {
            binding.showToolbar = shouldShowToolbar
        }
    }

    companion object {

        val DESTINATIONS_HIDE_TOOLBAR = listOf(
            R.id.aboutFragment
        )
    }
}

private val NavDestination.shouldShowToolbar: Boolean
    get() = !MainActivity.DESTINATIONS_HIDE_TOOLBAR.contains(id)