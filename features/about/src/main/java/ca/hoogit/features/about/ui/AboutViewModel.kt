package ca.hoogit.features.about.ui

import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import javax.inject.Inject

sealed class AboutAction : Action

internal class AboutViewModel @Inject constructor() : EiffelViewModel<AboutState, AboutAction>(
    initialState = AboutState(),
    update = update { state, _ -> state }
)