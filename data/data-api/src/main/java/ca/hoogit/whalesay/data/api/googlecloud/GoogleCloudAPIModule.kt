package ca.hoogit.whalesay.data.api.googlecloud

import ca.hoogit.whalesay.data.api.BuildConfig
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.TextToSpeechModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier annotation class GoogleCloudAPIKey

@Qualifier annotation class GoogleCloudAuthOkHttp

@Module(
    includes = [
        TextToSpeechModule::class
    ]
)
internal class GoogleCloudAPIModule {

    @Provides @GoogleCloudAPIKey
    fun provideGoogleCloudAPIKey(): String = BuildConfig.GOOGLE_CLOUD_API_KEY

    @Provides @GoogleCloudAuthOkHttp @Singleton
    fun provideGoogleCloudAuthOkHttp(
        builder: OkHttpClient.Builder,
        @GoogleCloudAPIKey key: String
    ): OkHttpClient = builder
        .addInterceptor(GoogleCloudAPI.AddGoogleCloudKeyParamInterceptor(key))
        .build()
}