package projekt.cloud.piece.genshin.elements.fragment.home

import androidx.coordinatorlayout.widget.CoordinatorLayout
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentHomeBinding

open class HomeLayoutCompat: BaseLayoutCompat<FragmentHomeBinding> {

    constructor(): super(null)
    constructor(binding: FragmentHomeBinding): super(binding)

    override fun getCompatImpl(binding: FragmentHomeBinding): HomeLayoutCompat =
        CompatImpl(binding)

    override fun getW600dpImpl(binding: FragmentHomeBinding): HomeLayoutCompat =
        W600dpImpl(binding)

    override fun getW1240dpImpl(binding: FragmentHomeBinding): HomeLayoutCompat =
        W1240dpImpl(binding)

    private class CompatImpl(binding: FragmentHomeBinding): HomeLayoutCompat(binding)

    private class W600dpImpl(binding: FragmentHomeBinding): HomeLayoutCompat(binding)

    private class W1240dpImpl(binding: FragmentHomeBinding): HomeLayoutCompat(binding)

}