package projekt.cloud.piece.genshin.elements.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.io.Closeable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object FragmentLifecycleUtil {

    fun <T> clearOnViewDestroy(): ReadWriteProperty<Fragment, T> =
        ClearOnViewDestroyProperty()

    fun <T: Closeable> closeOnViewDestroy(): ReadWriteProperty<Fragment, T> =
        CloseOnViewDestroyProperty()

    private open class ClearOnViewDestroyProperty<T>: ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {

        private var _instance: T? = null
        protected val instance: T
            get() = _instance!!

        override fun onDestroy(owner: LifecycleOwner) {
            _instance = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>) = instance

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
            thisRef.viewLifecycleOwner.lifecycle.addObserver(this)
            _instance = value
        }

    }

    private class CloseOnViewDestroyProperty<T: Closeable>: ClearOnViewDestroyProperty<T>() {

        override fun onDestroy(owner: LifecycleOwner) {
            instance.close()
            super.onDestroy(owner)
        }

    }

}