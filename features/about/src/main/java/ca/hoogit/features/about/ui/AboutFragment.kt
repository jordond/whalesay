package ca.hoogit.features.about.ui

import androidx.annotation.StringRes
import ca.hoogit.coreview.fragment.BindableFragment
import ca.hoogit.coreview.util.ktx.launchChromeTab
import ca.hoogit.coreview.viewmodel.injectedViewModel
import ca.hoogit.features.about.R
import ca.hoogit.features.about.databinding.FragmentAboutBinding
import ca.hoogit.ktx.launchViewIntent
import ca.hoogit.ktx.onClick
import ca.hoogit.ktx.showMessage
import ca.hoogit.ktx.util.openRateApp

internal class AboutFragment : BindableFragment<FragmentAboutBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_about

    private val viewModel by injectedViewModel<AboutViewModel>()

    override fun bindData() {
        binding.vm = viewModel
    }

    override fun setupViews() = with(binding) {
        txtBroughtBy.onClick { launchChromeTab(R.string.author_url) }
        btnViewWebsite.onClick { launchChromeTab(R.string.app_url) }
        btnSource.onClick { launchChromeTab(R.string.git_url) }

        btnInspiredBy.onClick {
            requireContext().launchViewIntent("http://${getString(R.string.inspired_by_url)}")
        }

        btnRate.onClick { openRateApp(requireContext()) }
        btnShare.onClick { binding.root.showMessage("Coming soon!") }
    }

    private fun launchChromeTab(@StringRes url: Int) =
        requireContext().launchChromeTab(getString(url))
}