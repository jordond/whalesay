package ca.hoogit.whalesay.data.api.googlecloud

import okhttp3.Interceptor
import okhttp3.Response

object GoogleCloudAPI {

    const val URL_TEXT_TO_SPEECH = "https://texttospeech.googleapis.com/v1/"

    const val URL_SPEECH_TO_TEXT = "https://speech.googleapis.com/v1/"

    class AddGoogleCloudKeyParamInterceptor(private val key: String) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response =
            chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("key", key)
                .build().let { url ->
                    chain.proceed(chain.request().newBuilder().url(url).build())
                }
    }
}
