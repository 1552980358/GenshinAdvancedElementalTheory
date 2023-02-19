package projekt.cloud.piece.genshin.elements.fragment.home

import androidx.coordinatorlayout.widget.CoordinatorLayout
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentHomeBinding

open class HomeLayoutCompat(
    binding: FragmentHomeBinding
): BaseLayoutCompat<FragmentHomeBinding>(binding) {

    override val compatImpl: HomeLayoutCompat
        get() = HomeLayoutCompatImpl(binding)

    override val w600dpImpl: HomeLayoutCompat
        get() = HomeLayoutCompatW600dpImpl(binding)

    override val w1240dpImpl: HomeLayoutCompat
        get() = HomeLayoutCompatW1240dpImpl(binding)

    private class HomeLayoutCompatImpl(binding: FragmentHomeBinding): HomeLayoutCompat(binding)

    private class HomeLayoutCompatW600dpImpl(binding: FragmentHomeBinding): HomeLayoutCompat(binding)

    private class HomeLayoutCompatW1240dpImpl(binding: FragmentHomeBinding): HomeLayoutCompat(binding)

}