package ca.hoogit.ktx

import android.content.Context
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.annotation.TransitionRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes color: Int): Int = ContextCompat.getColor(this, color)

fun Context.string(@StringRes id: Int, vararg formatArgs: Any):
    String = resources.getString(id, *formatArgs)

fun Context.int(@IntegerRes int: Int): Int = resources.getInteger(int)

fun Context.dimen(@DimenRes dimen: Int): Float = resources.getDimension(dimen)

fun Context.inflateTransition(@TransitionRes id: Int): Transition =
    TransitionInflater.from(this).inflateTransition(id)

val Context.inputManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
