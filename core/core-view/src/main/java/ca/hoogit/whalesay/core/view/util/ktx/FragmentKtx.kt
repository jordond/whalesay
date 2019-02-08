package ca.hoogit.whalesay.core.view.util.ktx

import androidx.fragment.app.Fragment
import ca.hoogit.whalesay.core.view.lib.HasFAB
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun Fragment.getProvidedFAB(): FloatingActionButton? = (activity as? HasFAB)?.getFAB()
