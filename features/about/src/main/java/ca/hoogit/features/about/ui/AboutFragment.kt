package ca.hoogit.features.about.ui

import androidx.annotation.StringRes
import ca.hoogit.whalesay.coreview.fragment.BindableFragment
import ca.hoogit.whalesay.coreview.util.ktx.launchChromeTab
import ca.hoogit.whalesay.coreview.viewmodel.injectedViewModel
import ca.hoogit.features.about.R
import ca.hoogit.features.about.databinding.FragmentAboutBinding
import ca.hoogit.whalesay.ktx.launchViewIntent
import ca.hoogit.whalesay.ktx.onClick
import ca.hoogit.whalesay.ktx.showMessage
import ca.hoogit.whalesay.ktx.util.openRateApp

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