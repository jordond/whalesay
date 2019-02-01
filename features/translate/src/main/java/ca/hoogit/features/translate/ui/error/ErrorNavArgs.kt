package ca.hoogit.features.translate.ui.error

import ca.hoogit.whalesay.coreview.lib.NavFragmentArgs
import ca.hoogit.features.translate.ui.error.model.ErrorType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorNavArgs(val error: ErrorType) : NavFragmentArgs