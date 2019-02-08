package com.worldturtlemedia.whalesay.features.translate.ui.error

import com.worldturtlemedia.whalesay.core.view.lib.NavFragmentArgs
import com.worldturtlemedia.whalesay.features.translate.ui.error.model.ErrorType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorNavArgs(val error: ErrorType) : NavFragmentArgs
