package ca.hoogit.whalesay.data.api.googlecloud.texttospeech

import ca.hoogit.whalesay.data.api.googlecloud.GoogleCloudAPI
import ca.hoogit.whalesay.data.api.googlecloud.GoogleCloudAuthOkHttp
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.datasource.TextToSpeechDefaultDataSource
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.datasource.TextToSpeechService
import ca.hoogit.whalesay.data.api.di.NetworkModule
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier annotation class RetrofitGoogleTextToSpeech

@Module
internal class TextToSpeechModule {

    @Provides @RetrofitGoogleTextToSpeech @Singleton
    fun provideTextToSpeechRetrofit(
        @GoogleCloudAuthOkHttp client: OkHttpClient,
        moshi: Moshi
    ): Retrofit = NetworkModule
        .createRetrofitClient(client, moshi, GoogleCloudAPI.URL_TEXT_TO_SPEECH)

    @Provides @Singleton
    fun provideTextToSpeechService(
        @RetrofitGoogleTextToSpeech retrofit: Retrofit
    ): TextToSpeechService = retrofit.create()

    @Provides @Singleton
    fun provideTextToSpeechDefaultDataSource(
        service: TextToSpeechService
    ): TextToSpeechDataSource = TextToSpeechDefaultDataSource(service)
}