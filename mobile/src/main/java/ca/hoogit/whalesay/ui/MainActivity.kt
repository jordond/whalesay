package ca.hoogit.whalesay.ui

import android.os.Bundle
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ca.hoogit.coreview.activity.BindableActivity
import ca.hoogit.coreview.util.onDestinationChanged
import ca.hoogit.coreview.viewmodel.getViewModel
import ca.hoogit.whalesay.NavigationMobileDirections
import ca.hoogit.whalesay.R
import ca.hoogit.whalesay.databinding.ActivityMainBinding

class MainActivity : BindableActivity<ActivityMainBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    private val viewModel by lazy { getViewModel<MainViewModel>() }

    private val navController by lazy { findNavController(R.id.navHost) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
    }

    override fun setupViews() {
        setupToolbar()
        setupBottomBar()
        setupNavListener()
    }

    private fun setupToolbar() = with(binding.toolbar) {
        setSupportActionBar(this)
        setupWithNavController(navController)
    }

    private fun setupBottomBar() = with(binding.viewBottomBar) {
        replaceMenu(R.menu.menu_mobile)
        setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.aboutFragment ->
                    navController.navigate(NavigationMobileDirections.showAboutScreen())
            }
            true
        }
    }

    private fun setupNavListener() = with(navController) {
        onDestinationChanged {
            binding.showToolbar = shouldShowToolBar
            binding.showBottomBar = shouldShowBottomBar

            with(binding.btnFAB) {
                if (shouldShowBottomBar) show() else hide()
            }
        }
    }

    companion object {

        val DESTINATIONS_HIDE_TOOLBAR = listOf(
            R.id.translateFragment
        )

        val DESTINATIONS_HIDE_BOTTOM_BAR = listOf(
            R.id.aboutFragment
        )
    }
}

private val NavDestination.shouldShowToolBar: Boolean
    get() = !MainActivity.DESTINATIONS_HIDE_TOOLBAR.contains(id)

private val NavDestination.shouldShowBottomBar: Boolean
    get() = !MainActivity.DESTINATIONS_HIDE_BOTTOM_BAR.contains(id)