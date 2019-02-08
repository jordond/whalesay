package com.worldturtlemedia.whalesay.features.about.ui

import com.worldturtlemedia.whalesay.features.about.util.AppInfo
import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.State
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import javax.inject.Inject

data class AboutState(
    val info: AppInfo = AppInfo()
) : State

sealed class AboutAction : Action

internal class AboutViewModel @Inject constructor() : EiffelViewModel<AboutState, AboutAction>(
    initialState = AboutState(),
    update = update { this }
)
