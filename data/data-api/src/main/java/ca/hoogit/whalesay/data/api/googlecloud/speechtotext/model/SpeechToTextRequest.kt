package ca.hoogit.whalesay.data.api.googlecloud.speechtotext.model

/**
 * See documentation:
 * https://cloud.google.com/speech-to-text/docs/reference/rest/v1/RecognitionConfig
 */

data class SpeechToTextRequest(
    val config: RecognitionConfig,
    val audio: RecognitionAudio
)

data class RecognitionConfig(
    val encoding: Any,
    val languageCode: String = "en-US",
    val sampleRateHertz: Number = 16_000
)

data class RecognitionAudio(
    val content: String
)
