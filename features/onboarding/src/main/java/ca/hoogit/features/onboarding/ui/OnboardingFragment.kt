package ca.hoogit.features.onboarding.ui

import androidx.navigation.fragment.findNavController
import ca.hoogit.coreview.bindingadapters.visibleOrGone
import ca.hoogit.coreview.fragment.BindableFragment
import ca.hoogit.coreview.state.MicPermissionState
import ca.hoogit.coreview.state.MicPermissionState.Denied
import ca.hoogit.coreview.state.MicPermissionState.Granted
import ca.hoogit.coreview.state.MicPermissionState.Pending
import ca.hoogit.coreview.state.MicPermissionState.PermanentlyDenied
import ca.hoogit.coreview.state.microphonePermissionState
import ca.hoogit.coreview.state.requestMicrophonePermission
import ca.hoogit.coreview.viewmodel.injectedViewModel
import ca.hoogit.features.onboarding.R
import ca.hoogit.features.onboarding.databinding.FragmentOnboardingBinding
import ca.hoogit.features.onboarding.ui.OnboardingEvents.NavigateHome
import ca.hoogit.features.onboarding.ui.OnboardingEvents.RequestAudioPermissions
import ca.hoogit.ktx.onClick
import com.etiennelenhart.eiffel.state.extension.observe
import com.etiennelenhart.eiffel.state.peek

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
        onGranted = { viewModel.updateMicPermissionStatus(MicPermissionState.Granted) },
        onDenied = { isPermanent ->
            val state =
                if (isPermanent) MicPermissionState.PermanentlyDenied
                else MicPermissionState.Denied

            viewModel.updateMicPermissionStatus(state)
        }
    )
}