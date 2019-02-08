package ca.hoogit.whalesay.features.about.ui

import ca.hoogit.whalesay.features.about.util.AppInfo
import com.etiennelenhart.eiffel.state.State

data class AboutState(
    val info: AppInfo = AppInfo()
) : State
