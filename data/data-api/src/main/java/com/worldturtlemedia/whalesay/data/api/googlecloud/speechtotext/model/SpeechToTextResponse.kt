package com.worldturtlemedia.whalesay.data.api.googlecloud.speechtotext.model

data class SpeechToTextResponse(
    val results: List<Alternatives>
)

data class Alternatives(
    val alternatives: List<Alternative>
)

data class Alternative(
    val transcript: String,
    val confidence: Double
)

internal fun SpeechToTextResponse.firstResult() =
    results.firstOrNull()?.alternatives?.firstOrNull()
