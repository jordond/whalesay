package com.worldturtlemedia.whalesay.features.translate.ui.error

import androidx.navigation.fragment.findNavController
import com.etiennelenhart.eiffel.state.extension.observe
import com.etiennelenhart.eiffel.state.peek
import com.worldturtlemedia.whalesay.core.ktx.navArgs
import com.worldturtlemedia.whalesay.core.ktx.onClick
import com.worldturtlemedia.whalesay.core.view.fragment.BindableFragment
import com.worldturtlemedia.whalesay.core.view.viewmodel.injectedViewModel
import com.worldturtlemedia.whalesay.features.translate.R
import com.worldturtlemedia.whalesay.features.translate.databinding.FragmentTranslateErrorBinding
import com.worldturtlemedia.whalesay.features.translate.ui.error.state.ErrorBindingState
import com.worldturtlemedia.whalesay.features.translate.ui.error.state.ErrorNavEvents

class ErrorFragment : BindableFragment<FragmentTranslateErrorBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_translate_error

    private val viewModel by injectedViewModel<ErrorViewModel>()

    private val args by navArgs<ErrorFragmentArgs>()

    override fun setupViews() {
        binding.state = ErrorBindingState()

        binding.btnTryAgain.onClick { viewModel.onTryAgainClicked() }
    }

    override fun subscribeViewModel() = with(viewModel) {
        dispatch(ErrorAction.Init(args.data.error))

        state.observe(owner) { state ->
            binding.state = ErrorBindingState.mapping.invoke(state, binding.state)
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
