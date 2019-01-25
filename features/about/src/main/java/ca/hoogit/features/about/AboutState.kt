package ca.hoogit.features.about

import ca.hoogit.features.about.util.AppInfo
import com.etiennelenhart.eiffel.state.State

data class AboutState(
    val info: AppInfo = AppInfo()
) : State