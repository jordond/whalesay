package ca.hoogit.features.translate.ui.translate

import ca.hoogit.coreview.fragment.BindableFragment
import ca.hoogit.features.translate.R
import ca.hoogit.features.translate.databinding.FragmentTranslateBinding
import ca.hoogit.features.translate.ui.error.ErrorNavArgs
import ca.hoogit.features.translate.ui.error.model.ErrorType
import ca.hoogit.ktx.navigateTo

class TranslateFragment : BindableFragment<FragmentTranslateBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_translate

    override fun setupViews() = with(binding) {
//        txtTest.onClick { }
//
//        btnTestGeneric.onClick { showErrorScreen(ErrorType.Generic) }
//        btnTestGoogle.onClick { showErrorScreen(ErrorType.Google) }
//        btnTestNetwork.onClick { showErrorScreen(ErrorType.Network) }
    }

    private fun showErrorScreen(error: ErrorType) {
        navigateTo {
            TranslateFragmentDirections.showErrorScreen(ErrorNavArgs(error))
        }
    }
}