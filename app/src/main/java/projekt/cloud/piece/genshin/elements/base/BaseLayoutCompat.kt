package projekt.cloud.piece.genshin.elements.base

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewbinding.ViewBinding
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.COMPACT
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.EXPANDED
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.MEDIUM
import projekt.cloud.piece.genshin.elements.util.ScreenDensity.ScreenDensityUtil.screenDensity

abstract class BaseLayoutCompat<out VB: ViewBinding>(private var _binding: VB?) {

    companion object {

        inline fun <reified VB: ViewBinding, reified BLC: BaseLayoutCompat<VB>> VB.layoutCompat(context: Context) =
            BLC::class.java.getConstructor(VB::class.java).newInstance(this).getImpl(context)

        fun <BLC: BaseLayoutCompat<*>> BLC.getImpl(context: Context): BLC {
            return when (context.screenDensity) {
                COMPACT -> compatImpl
                MEDIUM -> w600dpImpl
                EXPANDED -> w1240dpImpl
            } as BLC
        }

    }

    protected val binding: VB
        get() = _binding!!

    protected abstract val snackBarContainer: CoordinatorLayout

    protected abstract val compatImpl: BaseLayoutCompat<*>
    protected abstract val w600dpImpl: BaseLayoutCompat<*>
    protected abstract val w1240dpImpl: BaseLayoutCompat<*>

    fun destroy() {
        _binding = null
    }

}