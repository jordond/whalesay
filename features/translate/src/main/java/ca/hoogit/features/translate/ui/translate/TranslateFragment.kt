package ca.hoogit.features.translate.ui.translate

import ca.hoogit.coreview.fragment.BindableFragment
import ca.hoogit.features.translate.R
import ca.hoogit.features.translate.databinding.FragmentTranslateBinding
import ca.hoogit.ktx.onClick

class TranslateFragment : BindableFragment<FragmentTranslateBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_translate

    override fun setupViews() = with(binding) {
        txtTest.onClick { }
    }
}