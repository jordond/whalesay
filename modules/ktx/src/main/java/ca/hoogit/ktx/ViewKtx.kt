package ca.hoogit.ktx

import android.view.View
import android.view.ViewGroup
import androidx.annotation.Px
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import timber.log.Timber

fun View.setMarginTop(margin: Int) {
    updateMargins(top = margin)
}

fun View.updateMargins(
    @Px left: Int = marginLeft,
    @Px top: Int = marginTop,
    @Px right: Int = marginRight,
    @Px bottom: Int = marginBottom
) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        leftMargin = left
        topMargin = top
        rightMargin = right
        bottomMargin = bottom
    }?.let { layoutParams = it }
}

fun View.onClick(listener: (View) -> Unit) {
    this.setOnClickListener(listener)
}

fun <T : View> View.onClickTyped(listener: (T) -> Unit) {
    @Suppress("UNCHECKED_CAST")
    this.setOnClickListener {
        (this as? T)?.let { view -> listener(view) }
    }
}

fun View.hideKeyboard() {
    try {
        context.inputManager.hideSoftInputFromWindow(this.windowToken, 0)
    } catch (error: Error) {
        Timber.w("Unable to hide keyboard, keyboard might not be open")
    }
}

fun View.showKeyboard() {
    try {
        context.inputManager.showSoftInput(this, 0)
    } catch (error: Error) {
        Timber.w("Unable to hide keyboard, keyboard might not be open")
    }
}

fun View.requestFocusWithKeyboard() {
    requestFocus()
    showKeyboard()
}

fun View.clearFocusAndHideKeyboard() {
    clearFocus()
    hideKeyboard()
}

fun View.onLayoutChange(block: (View) -> Unit) {
    addOnLayoutChangeListener { v, _, _, _, _, _, _, _, _ ->
        block(v)
    }
}