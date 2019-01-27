package ca.hoogit.features.translate.ui.error

import androidx.navigation.fragment.findNavController
import ca.hoogit.coreview.fragment.BindableFragment
import ca.hoogit.coreview.viewmodel.injectedViewModel
import ca.hoogit.features.translate.R
import ca.hoogit.features.translate.databinding.FragmentTranslateErrorBinding
import ca.hoogit.features.translate.ui.error.state.ErrorBindingState
import ca.hoogit.features.translate.ui.error.state.ErrorNavEvents
import ca.hoogit.ktx.navArgs
import ca.hoogit.ktx.onClick
import com.etiennelenhart.eiffel.binding.extension.observeState
import com.etiennelenhart.eiffel.state.peek

class ErrorFragment : BindableFragment<FragmentTranslateErrorBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_translate_error

    private val viewModel by injectedViewModel<ErrorViewModel>()

    private val bindingState = ErrorBindingState()

    private val args by navArgs<ErrorFragmentArgs>()

    override fun setupViews() {
        binding.state = bindingState

        binding.btnTryAgain.onClick { viewModel.onTryAgainClicked() }
    }

    override fun subscribeViewModel() = with(viewModel) {
        dispatch(ErrorAction.Init(args.data.error))
        observeState(owner, bindingState) { state ->
            state.navEvent?.peek(::handleNavEvents)
        }
    }

    private fun handleNavEvents(event: ErrorNavEvents): Boolean = when (event) {
        is ErrorNavEvents.Back -> {
            findNavController().popBackStack()
            true
        }
    }
}