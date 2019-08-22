package com.worldturtlemedia.whalesay.features.translate.ui.translate

import com.etiennelenhart.eiffel.state.extension.observe
import com.etiennelenhart.eiffel.state.peek
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.worldturtlemedia.whalesay.core.ktx.doAfterTextChanged
import com.worldturtlemedia.whalesay.core.ktx.navigateTo
import com.worldturtlemedia.whalesay.core.ktx.onClick
import com.worldturtlemedia.whalesay.core.view.bindingadapters.visible
import com.worldturtlemedia.whalesay.core.view.bindingadapters.visibleOrGone
import com.worldturtlemedia.whalesay.core.view.fragment.BindableFragment
import com.worldturtlemedia.whalesay.core.view.state.canUseMic
import com.worldturtlemedia.whalesay.core.view.state.microphonePermissionState
import com.worldturtlemedia.whalesay.core.view.util.ktx.getProvidedFAB
import com.worldturtlemedia.whalesay.core.view.viewmodel.injectedViewModel
import com.worldturtlemedia.whalesay.features.translate.R
import com.worldturtlemedia.whalesay.features.translate.audio.PlayerState
import com.worldturtlemedia.whalesay.features.translate.audio.canPlay
import com.worldturtlemedia.whalesay.features.translate.databinding.FragmentTranslateBinding
import com.worldturtlemedia.whalesay.features.translate.ui.error.ErrorNavArgs
import com.worldturtlemedia.whalesay.features.translate.ui.error.model.ErrorType

class TranslateFragment : BindableFragment<FragmentTranslateBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_translate

    private val viewModel by injectedViewModel<TranslateViewModel>()

    private val fab by lazy { getProvidedFAB() }

    override fun setupViews() = with(binding) {
        txtTextInput.doAfterTextChanged { editable ->
            viewModel.updateHumanText(editable?.toString())
        }

        fab?.onClick { viewModel.onFabClicked() }

        Unit
    }

    // TODO - Hide Whale logo if keyboard is open
    override fun subscribeViewModel() = with(viewModel) {
        initialize(requireContext().microphonePermissionState)

        state.observe(owner) { state ->
            renderWhaleTextBox(state)
            fab?.let { renderFAB(it, state) }

            state.errorEvent?.peek { showErrorScreen(it.type) }
        }
    }

    private fun renderWhaleTextBox(state: TranslateState) {
        with(binding.txt2) {
            text = state.whaleText
            visibleOrGone = !state.whaleText.isEmpty()
            if (visible) binding.txtTextInput.requestFocus()
        }
        binding.imgLogo.visibleOrGone = state.whaleText.isEmpty()
    }

    private fun renderFAB(fab: FloatingActionButton, state: TranslateState) = with(state) {
        val imgRes = when {
            isRecording -> R.drawable.ic_stop
            audioPlayerState is PlayerState.Playing -> R.drawable.ic_stop
            (whaleText.isNotEmpty() && audioPlayerState.canPlay) -> R.drawable.ic_play
            micPermissionState.canUseMic -> R.drawable.ic_mic_white
            else -> null
        }?.apply { fab.setImageResource(this) }

        if (imgRes != null) fab.show() else fab.hide()
    }

    private fun showErrorScreen(error: ErrorType): Boolean {
        navigateTo {
            TranslateFragmentDirections.showErrorScreen(ErrorNavArgs(error))
        }

        return true
    }
}
