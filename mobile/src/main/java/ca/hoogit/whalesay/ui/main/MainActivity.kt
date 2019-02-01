package ca.hoogit.whalesay.ui.main

import android.os.Bundle
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ca.hoogit.whalesay.coreview.activity.BindableActivity
import ca.hoogit.whalesay.coreview.lib.HasFAB
import ca.hoogit.whalesay.coreview.util.onDestinationChanged
import ca.hoogit.whalesay.coreview.viewmodel.getViewModel
import ca.hoogit.whalesay.data.db.prefs.Prefs
import ca.hoogit.whalesay.NavigationMobileDirections
import ca.hoogit.whalesay.R
import ca.hoogit.whalesay.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BindableActivity<ActivityMainBinding>(), HasFAB {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getFAB(): FloatingActionButton = binding.btnFAB

    @Inject lateinit var prefs: Prefs

    private val viewModel by lazy { getViewModel<MainViewModel>() }

    private val navHostFragment by lazy { navHost as NavHostFragment }

    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun beforeSetup() {
        // TODO - Move the onboarding to it's own activity
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