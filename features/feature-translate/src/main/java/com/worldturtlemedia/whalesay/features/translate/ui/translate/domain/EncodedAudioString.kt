package com.worldturtlemedia.whalesay.features.translate.ui.translate.domain

data class EncodedAudioString(val data: String) {

    override fun toString(): String = "${this::class.java.simpleName}(length=${data.length}"
}
