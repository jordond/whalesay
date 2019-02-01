package ca.hoogit.whalesay.features.translate.ui.error.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class ErrorType : Parcelable {
    object None : ErrorType()
    object Generic : ErrorType()
    object Network : ErrorType()
    object Google : ErrorType()
    object SpeechToText : ErrorType()
    object TextToSpeech : ErrorType()
}