package ca.hoogit.whalesay.coreview.util.ktx

import androidx.fragment.app.Fragment
import ca.hoogit.whalesay.coreview.lib.HasFAB
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun Fragment.getProvidedFAB(): FloatingActionButton? = (activity as? HasFAB)?.getFAB()