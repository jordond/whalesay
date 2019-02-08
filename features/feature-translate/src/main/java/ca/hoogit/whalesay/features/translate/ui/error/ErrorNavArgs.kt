package ca.hoogit.whalesay.features.translate.ui.error

import ca.hoogit.whalesay.core.view.lib.NavFragmentArgs
import ca.hoogit.whalesay.features.translate.ui.error.model.ErrorType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorNavArgs(val error: ErrorType) : NavFragmentArgs
