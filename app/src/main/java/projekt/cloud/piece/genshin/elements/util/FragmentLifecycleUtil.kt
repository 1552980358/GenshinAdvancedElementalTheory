package projekt.cloud.piece.genshin.elements.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.io.Closeable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object FragmentLifecycleUtil {

    fun <T> Fragment.clearOnViewDestroy(): ReadWriteProperty<Fragment, T> =
        ClearOnViewDestroyProperty(this)

    fun <T: Closeable> Fragment.closeOnViewDestroy(): ReadWriteProperty<Fragment, T> =
        CloseOnViewDestroyProperty(this)

    private open class ClearOnViewDestroyProperty<T>(
        fragment: Fragment
    ): ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {

        init {
            fragment.viewLifecycleOwner.lifecycle.addObserver(this)
        }

        private var _instance: T? = null
        protected val instance: T
            get() = _instance!!

        override fun onDestroy(owner: LifecycleOwner) {
            _instance = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>) = instance

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
            _instance = value
        }

    }

    private class CloseOnViewDestroyProperty<T: Closeable>(
        fragment: Fragment
    ): ClearOnViewDestroyProperty<T>(fragment) {

        override fun onDestroy(owner: LifecycleOwner) {
            instance.close()
            super.onDestroy(owner)
        }

    }

}