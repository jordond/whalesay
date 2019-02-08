package ca.hoogit.whalesay.data.api.googlecloud.speechtotext

import ca.hoogit.whalesay.data.api.di.NetworkModule
import ca.hoogit.whalesay.data.api.googlecloud.GoogleCloudAPI
import ca.hoogit.whalesay.data.api.googlecloud.GoogleCloudAuthOkHttp
import ca.hoogit.whalesay.data.api.googlecloud.speechtotext.datasource.SpeechToTextDefaultDataSource
import ca.hoogit.whalesay.data.api.googlecloud.speechtotext.datasource.SpeechToTextService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier annotation class RetrofitSpeechToText

@Module
internal class SpeechToTextModule {

    @Provides @RetrofitSpeechToText @Singleton
    fun provideSpeechToTextRetrofit(
        @GoogleCloudAuthOkHttp client: OkHttpClient,
        moshi: Moshi
    ): Retrofit = NetworkModule
        .createRetrofitClient(client, moshi, GoogleCloudAPI.URL_SPEECH_TO_TEXT)

    @Provides @Singleton
    fun provideSpeechToTextService(
        @RetrofitSpeechToText retrofit: Retrofit
    ): SpeechToTextService = retrofit.create()

    @Provides @Singleton
    fun provideSpeechToTextDefaultDataSource(
        service: SpeechToTextService
    ): SpeechToTextDataSource = SpeechToTextDefaultDataSource(service)
}
