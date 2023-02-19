package projekt.cloud.piece.genshin.elements.base

import android.content.Context
import androidx.viewbinding.ViewBinding
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.COMPACT
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.EXPANDED
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.MEDIUM
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.ScreenDensityUtil.screenDensity

abstract class BaseLayoutCompat<out VB: ViewBinding> protected constructor(private var _binding: VB?) {

    companion object {

        inline fun <reified VB: ViewBinding, reified BLC: BaseLayoutCompat<VB>> VB.layoutCompat(context: Context): BLC =
            BLC::class.java.getConstructor().newInstance().getImpl(this, context)

        fun <VB: ViewBinding, BLC: BaseLayoutCompat<VB>> BLC.getImpl(binding: VB, context: Context): BLC {
            @Suppress("UNCHECKED_CAST")
            return when (context.screenDensity) {
                COMPACT -> getCompatImpl(binding)
                MEDIUM -> getW600dpImpl(binding)
                EXPANDED -> getW1240dpImpl(binding)
            } as BLC
        }

    }

    protected val binding: VB
        get() = _binding!!

    protected abstract fun getCompatImpl(binding: @UnsafeVariance VB): BaseLayoutCompat<VB>
    protected abstract fun getW600dpImpl(binding: @UnsafeVariance VB): BaseLayoutCompat<VB>
    protected abstract fun getW1240dpImpl(binding: @UnsafeVariance VB): BaseLayoutCompat<VB>

    fun destroy() {
        _binding = null
    }

}