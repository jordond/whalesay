package com.worldturtlemedia.whalesay.features.about.ui

import androidx.annotation.StringRes
import com.worldturtlemedia.whalesay.core.ktx.launchViewIntent
import com.worldturtlemedia.whalesay.core.ktx.onClick
import com.worldturtlemedia.whalesay.core.ktx.showMessage
import com.worldturtlemedia.whalesay.core.ktx.util.openRateApp
import com.worldturtlemedia.whalesay.core.view.fragment.BindableFragment
import com.worldturtlemedia.whalesay.core.view.util.ktx.launchChromeTab
import com.worldturtlemedia.whalesay.core.view.viewmodel.injectedViewModel
import com.worldturtlemedia.whalesay.features.about.R
import com.worldturtlemedia.whalesay.features.about.databinding.FragmentAboutBinding

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
