package projekt.cloud.piece.genshin.elements.base

import android.content.Context
import androidx.viewbinding.ViewBinding
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.COMPACT
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.EXPANDED
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.MEDIUM
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.ScreenDensityUtil.screenDensity

abstract class BaseLayoutCompat<out VB: ViewBinding>(private var _binding: VB?) {

    companion object {

        inline fun <reified VB: ViewBinding, reified BLC: BaseLayoutCompat<VB>> VB.layoutCompat(context: Context): BLC =
            BLC::class.java.getConstructor(VB::class.java).newInstance(this).getImpl(context)

        fun <VB: ViewBinding, BLC: BaseLayoutCompat<VB>> BLC.getImpl(context: Context): BLC {
            @Suppress("UNCHECKED_CAST")
            return when (context.screenDensity) {
                COMPACT -> compatImpl
                MEDIUM -> w600dpImpl
                EXPANDED -> w1240dpImpl
            } as BLC
        }

    }

    protected val binding: VB
        get() = _binding!!

    protected abstract val compatImpl: BaseLayoutCompat<VB>
    protected abstract val w600dpImpl: BaseLayoutCompat<VB>
    protected abstract val w1240dpImpl: BaseLayoutCompat<VB>

    fun destroy() {
        _binding = null
    }

}