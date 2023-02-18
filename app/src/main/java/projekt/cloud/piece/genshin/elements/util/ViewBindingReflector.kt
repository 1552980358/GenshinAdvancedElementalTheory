package projekt.cloud.piece.genshin.elements.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method

class ViewBindingReflector(private val viewBindingClass: Class<out ViewBinding>) {

    companion object LayoutBindingReflectorUtil {

        private const val VIEW_BINDING_METHOD_INFLATE = "inflate"

        @JvmStatic
        fun <VB: ViewBinding> Class<out ViewBinding>.inflate(
            layoutInflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean = false
        ): VB = ViewBindingReflector(this).inflate(layoutInflater, parent, attachToRoot)

        @JvmStatic
        fun <VB: ViewBinding> Class<VB>.inflate(layoutInflater: LayoutInflater): VB =
            inflate(layoutInflater, null)

    }

    private val reflectMethodInflate: Method =
        viewBindingClass.getDeclaredMethod(
            VIEW_BINDING_METHOD_INFLATE, LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
        )

    private val methodInflate by lazy { reflectMethodInflate }

    @Suppress("UNCHECKED_CAST")
    fun <VB: ViewBinding> inflate(
        layoutInflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean = false
    ) = methodInflate.invoke(null, layoutInflater, parent, attachToRoot) as VB

    @Suppress("UNCHECKED_CAST")
    fun <VB: ViewBinding> inflate(layoutInflater: LayoutInflater) =
        inflate(layoutInflater, null) as VB

}