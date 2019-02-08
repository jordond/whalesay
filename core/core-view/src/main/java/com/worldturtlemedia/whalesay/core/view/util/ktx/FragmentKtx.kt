package com.worldturtlemedia.whalesay.core.view.util.ktx

import androidx.fragment.app.Fragment
import com.worldturtlemedia.whalesay.core.view.lib.HasFAB
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun Fragment.getProvidedFAB(): FloatingActionButton? = (activity as? HasFAB)?.getFAB()
