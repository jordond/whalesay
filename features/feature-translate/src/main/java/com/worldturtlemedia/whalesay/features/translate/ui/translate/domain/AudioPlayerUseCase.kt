package com.worldturtlemedia.whalesay.features.translate.ui.translate.domain

import com.worldturtlemedia.whalesay.core.util.Async
import com.worldturtlemedia.whalesay.core.util.Fail
import com.worldturtlemedia.whalesay.core.util.Success
import com.worldturtlemedia.whalesay.core.util.mutableLiveDataOf
import com.worldturtlemedia.whalesay.features.translate.audio.AudioPlayer
import com.worldturtlemedia.whalesay.features.translate.audio.PlayerState
import javax.inject.Inject

class AudioPlayerUseCase @Inject constructor(
    private val audioPlayer: AudioPlayer
) {

    val playerState = mutableLiveDataOf<PlayerState>(PlayerState.None)

    init {
        audioPlayer.setOnStateChangedListener { playerState.postValue(it) }
    }

    suspend fun play(path: String): Async<Boolean> = try {
        Success(audioPlayer.playFile(path))
    } catch (error: Throwable) {
        Fail(error)
    }

    fun stop() = audioPlayer.stop()

    fun release() {
        audioPlayer.destroy()
    }
}
