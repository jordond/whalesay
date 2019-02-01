package ca.hoogit.whalesay.data.api.api.googlecloud

import ca.hoogit.whalesay.data.api.BuildConfig
import ca.hoogit.whalesay.data.api.api.googlecloud.texttospeech.TextToSpeechModule
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Qualifier annotation class GoogleCloudAPIKey

@Module(
    includes = [
        TextToSpeechModule::class
    ]
)
internal class GoogleCloudAPIModule {

    @Provides @GoogleCloudAPIKey
    fun provideGoogleCloudAPIKey(): String = BuildConfig.GOOGLE_CLOUD_API_KEY
}