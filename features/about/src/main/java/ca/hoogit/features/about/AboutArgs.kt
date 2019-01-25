package ca.hoogit.features.about

import ca.hoogit.coreview.lib.NavFragmentArgs
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AboutArgs(
    val appName: String
) : NavFragmentArgs