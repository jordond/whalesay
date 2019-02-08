package com.worldturtlemedia.whalesay.features.onboarding.ui

import androidx.navigation.fragment.findNavController
import com.etiennelenhart.eiffel.state.extension.observe
import com.etiennelenhart.eiffel.state.peek
import com.worldturtlemedia.whalesay.core.ktx.onClick
import com.worldturtlemedia.whalesay.core.view.bindingadapters.visibleOrGone
import com.worldturtlemedia.whalesay.core.view.fragment.BindableFragment
import com.worldturtlemedia.whalesay.core.view.state.MicPermissionState.Denied
import com.worldturtlemedia.whalesay.core.view.state.MicPermissionState.Granted
import com.worldturtlemedia.whalesay.core.view.state.MicPermissionState.Pending
import com.worldturtlemedia.whalesay.core.view.state.MicPermissionState.PermanentlyDenied
import com.worldturtlemedia.whalesay.core.view.state.microphonePermissionState
import com.worldturtlemedia.whalesay.core.view.state.requestMicrophonePermission
import com.worldturtlemedia.whalesay.core.view.viewmodel.injectedViewModel
import com.worldturtlemedia.whalesay.features.onboarding.R
import com.worldturtlemedia.whalesay.features.onboarding.databinding.FragmentOnboardingBinding
import com.worldturtlemedia.whalesay.features.onboarding.ui.OnboardingEvents.NavigateHome
import com.worldturtlemedia.whalesay.features.onboarding.ui.OnboardingEvents.RequestAudioPermissions

class OnboardingFragment : BindableFragment<FragmentOnboardingBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_onboarding

    private val viewModel by injectedViewModel<OnboardingViewModel>()

    override fun setupViews() = with(binding) {
        btnFab.hide()
        btnFab.onClick { viewModel.onButtonClicked() }
        btnContinue.onClick { viewModel.onButtonClicked() }
        btnHelpIcon.onClick { viewModel.dispatch(OnboardingAction.ToggleRational) }
    }

    override fun subscribeViewModel() = with(viewModel) {
        // TODO - this should be extracted out to a Use-case
        // We should be using the injected Context in a use-case to gather this information
        initMicPermissionsStatus(requireContext().microphonePermissionState)

        state.observe(owner) { state ->
            state.event?.peek(::handleEvents)

            renderFAB(state)
            renderRationalText(state)
        }
    }

    private fun renderFAB(state: OnboardingState) = with(binding.btnFab) {
        val iconRes = when (state.micState) {
            Pending -> R.drawable.ic_mic_none_white
            Granted -> R.drawable.ic_mic_white
            Denied, PermanentlyDenied -> R.drawable.ic_mic_off_white
        }

        setImageResource(iconRes)

        isEnabled = state.micState != Granted

        binding.btnHelpIcon.visibleOrGone = state.micState != PermanentlyDenied

        if (state.micState is PermanentlyDenied) hide() else show()
    }

    private fun renderRationalText(state: OnboardingState) = with(binding.txtPermissionRational) {
        visibleOrGone = state.micState !is PermanentlyDenied && state.showRationalMessage
    }

    private fun handleEvents(event: OnboardingEvents): Boolean = when (event) {
        is NavigateHome -> findNavController().navigateUp()
        is RequestAudioPermissions -> {
            requestAudioPermissions()
            true
        }
    }

    private fun requestAudioPermissions() = requireContext().requestMicrophonePermission(
        onGranted = { viewModel.updateMicPermissionStatus(Granted) },
        onDenied = { isPermanent ->
            val state =
                if (isPermanent) PermanentlyDenied
                else Denied

            viewModel.updateMicPermissionStatus(state)
        }
    )
}
