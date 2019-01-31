package ca.hoogit.features.translate.util

import ca.hoogit.ktx.repeat

// TODO - Also strip out special characters (except .)
object HumanToWhaleUtil {

    private val REGEX_CONSONANTS = Regex("[b-df-hj-np-tv-z]", RegexOption.IGNORE_CASE)

    private val CHAR_TWEAK_MAP = mapOf(
        'a' to 1,
        'e' to 2,
        'i' to 1,
        'o' to 3,
        'u' to 3
    )

    fun convertHumanTextToWhalese(text: String): String {
        return text.replace(REGEX_CONSONANTS, "")
            .map { it.repeat(CHAR_TWEAK_MAP[it] ?: 1) }
            .joinToString("")
    }
}

fun String.toWhalese() = HumanToWhaleUtil.convertHumanTextToWhalese(this).trim()
