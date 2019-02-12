package com.worldturtlemedia.whalesay.features.translate.audio

import android.media.AudioAttributes
import android.media.MediaPlayer
import com.github.ajalt.timberkt.e
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

sealed class PlayerState {
    object None : PlayerState()
    object Idle : PlayerState()
    object Playing : PlayerState()
    object Finished : PlayerState()
    object Released : PlayerState()
    object Error : PlayerState()
}

val PlayerState.canPlay: Boolean
    get() = this is PlayerState.Idle || this is PlayerState.Finished

class AudioPlayer @Inject constructor() {

    private var state: PlayerState = PlayerState.Idle
        set(value) {
            onStateChanged?.invoke(value)
            field = value
        }

    private val mediaPlayer: MediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
        )
        setOnCompletionListener {
            state = PlayerState.Finished
            reset()
        }
    }

    private var onStateChanged: ((state: PlayerState) -> Unit)? = null

    fun setOnStateChangedListener(block: (playerState: PlayerState) -> Unit) {
        onStateChanged = block
        block(state)
    }

    suspend fun playFile(filename: String): Boolean {
        return prepareMediaPlayer(filename) && start()
    }

    fun start(): Boolean = try {
        mediaPlayer.start()
        state = PlayerState.Playing
        true
    } catch (error: Throwable) {
        e(error) { "Unable to Start the MediaPlayer!" }
        false
    }

    fun stop(): Boolean = try {
        mediaPlayer.stop()
        mediaPlayer.reset()
        state = PlayerState.Idle
        true
    } catch (error: Throwable) {
        e(error) { "Unable to Stop the MediaPlayer!" }
        false
    }

    fun destroy() {
        try {
            mediaPlayer.stop()
            mediaPlayer.release()
            state = PlayerState.Released
        } catch (error: Throwable) {
            e(error) { "Unable to release the MediaPlayer" }
        }
    }

    private suspend fun prepareMediaPlayer(filename: String): Boolean =
        suspendCancellableCoroutine { continuation ->
            try {
                mediaPlayer.apply {
                    setDataSource(filename)
                    setOnPreparedListener {
                        state = PlayerState.Idle
                        continuation.resume(true)
                    }
                    prepareAsync()
                }
            } catch (error: Throwable) {
                e(error) { "Unable to prepare media player with $filename" }
                continuation.resume(false)
            }
        }
}
