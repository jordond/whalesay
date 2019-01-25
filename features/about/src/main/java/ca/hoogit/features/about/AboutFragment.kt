package ca.hoogit.features.about

import ca.hoogit.coreview.fragment.BindableFragment
import ca.hoogit.coreview.lib.NavFragment
import ca.hoogit.coreview.viewmodel.injectedViewModel
import ca.hoogit.features.about.databinding.FragmentAboutBinding

internal class AboutFragment : BindableFragment<FragmentAboutBinding>(), NavFragment {

    override fun getLayoutRes(): Int = R.layout.fragment_about

    private val viewModel by injectedViewModel<AboutViewModel>()
}