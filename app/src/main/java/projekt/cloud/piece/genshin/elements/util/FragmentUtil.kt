package projekt.cloud.piece.genshin.elements.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

object FragmentUtil {

    @JvmStatic
    fun Fragment.setSupportActionBar(toolbar: Toolbar) =
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

}