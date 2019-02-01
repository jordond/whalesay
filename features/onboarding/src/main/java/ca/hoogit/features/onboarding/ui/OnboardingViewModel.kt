package ca.hoogit.features.onboarding.ui

import ca.hoogit.whalesay.coreview.state.MicPermissionState
import ca.hoogit.whalesay.coreview.state.MicPermissionState.Denied
import ca.hoogit.whalesay.coreview.state.MicPermissionState.Granted
import ca.hoogit.whalesay.coreview.state.MicPermissionState.Pending
import ca.hoogit.whalesay.coreview.state.MicPermissionState.PermanentlyDenied
import ca.hoogit.whalesay.coreview.util.ktx.currentState
import ca.hoogit.data.db.prefs.Prefs
import ca.hoogit.features.onboarding.ui.OnboardingAction.DispatchEvent
import ca.hoogit.features.onboarding.ui.OnboardingAction.UpdateMicPermission
import com.etiennelenhart.eiffel.interception.pipe
import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.State
import com.etiennelenhart.eiffel.state.ViewEvent
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import javax.inject.Inject

sealed class OnboardingEvents : ViewEvent() {
    class NavigateHome : OnboardingEvents()
    class RequestAudioPermissions : OnboardingEvents()
}

data class OnboardingState(
    val micState: MicPermissionState = Pending,
    val showRationalMessage: Boolean = false,
    val event: OnboardingEvents? = null
) : State

sealed class OnboardingAction : Action {
    data class UpdateMicPermission(val state: MicPermissionState) : OnboardingAction()
    object ToggleRational : OnboardingAction()
    data class DispatchEvent(val event: OnboardingEvents) : OnboardingAction()
}

private fun onNavigateUpdatePrefs(prefs: Prefs) = pipe<OnboardingState, OnboardingAction> { state, action ->
    if (action is DispatchEvent && action.event is OnboardingEvents.NavigateHome) {
        prefs.hasSeenOnboarding = true
    }
}

class OnboardingViewModel @Inject constructor(
    prefs: Prefs
) : EiffelViewModel<OnboardingState, OnboardingAction>(
    initialState = OnboardingState(),
    interceptions = listOf(
        onNavigateUpdatePrefs(prefs)
    ),
    update = update { state, action ->
        when (action) {
            is UpdateMicPermission -> state.copy(
                micState = action.state,
                showRationalMessage = action.state is Denied
            )
            is OnboardingAction.ToggleRational ->
                state.copy(showRationalMessage = !state.showRationalMessage)
            is DispatchEvent -> state.copy(event = action.event)
        }
    }
) {

    fun onButtonClicked() {
        val event = when (currentState.micState) {
            Pending, Denied -> OnboardingEvents.RequestAudioPermissions()
            Granted, PermanentlyDenied -> OnboardingEvents.NavigateHome()
        }

        dispatch(DispatchEvent(event))
    }

    /**
     * Initialize the Mic state.
     *
     * By default with Android the permission is set to denied, but the user has never
     * actually been prompted. So this will initialize the state with [Pending] if its [Denied],
     * or else with the passed in status
     *
     * @param[status] Initial state of the microphone permission
     */
    fun initMicPermissionsStatus(status: MicPermissionState) {
        if (currentState.micState !is Pending) return // Already initialized

        val permission = if (status is Denied) Pending else status
        updateMicPermissionStatus(permission)
    }

    fun updateMicPermissionStatus(status: MicPermissionState) {
        dispatch(UpdateMicPermission(status))
    }
}