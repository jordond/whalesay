package ca.hoogit.whalesay.ui

import android.os.Bundle
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ca.hoogit.coreview.activity.BindableActivity
import ca.hoogit.coreview.fragment.BaseFragment
import ca.hoogit.coreview.util.onDestinationChanged
import ca.hoogit.coreview.viewmodel.getViewModel
import ca.hoogit.data.db.prefs.Prefs
import ca.hoogit.whalesay.NavigationMobileDirections
import ca.hoogit.whalesay.R
import ca.hoogit.whalesay.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

fun BaseFragment.getFAB() = (activity as? MainActivity)?.fab

class MainActivity : BindableActivity<ActivityMainBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    @Inject lateinit var prefs: Prefs

    private val viewModel by lazy { getViewModel<MainViewModel>() }

    private val navHostFragment by lazy { navHost as NavHostFragment }

    private val navController by lazy { navHostFragment.navController }

    val fab by lazy { binding.btnFAB }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun beforeSetup() {
        if (!prefs.hasSeenOnboarding) {
            navController.navigate(NavigationMobileDirections.showOnboardingScreen())
        }
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
            R.id.onboardingFragment,
            R.id.translateFragment
        )

        val DESTINATIONS_HIDE_BOTTOM_BAR = listOf(
            R.id.onboardingFragment,
            R.id.aboutFragment
        )
    }
}

private val NavDestination.shouldShowToolBar: Boolean
    get() = !MainActivity.DESTINATIONS_HIDE_TOOLBAR.contains(id)

private val NavDestination.shouldShowBottomBar: Boolean
    get() = !MainActivity.DESTINATIONS_HIDE_BOTTOM_BAR.contains(id)