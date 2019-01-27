package ca.hoogit.features.onboarding.ui

import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.State
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel

data class OnboardingState(val mic: Boolean = false) : State

sealed class OnboardingAction : Action

class OnboardingViewModel : EiffelViewModel<OnboardingState, OnboardingAction>(
    initialState = OnboardingState(),
    update = update { state, action -> state }
)