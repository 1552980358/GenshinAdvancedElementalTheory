package projekt.cloud.piece.genshin.elements.fragment.home

import androidx.coordinatorlayout.widget.CoordinatorLayout
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentHomeBinding

open class HomeLayoutCompat(
    binding: FragmentHomeBinding
): BaseLayoutCompat<FragmentHomeBinding>(binding) {

    override val snackBarContainer: CoordinatorLayout
        get() = binding.coordinatorLayout

    override val compatImpl: BaseLayoutCompat<*>
        get() = HomeLayoutCompatImpl(binding)

    override val w600dpImpl: BaseLayoutCompat<*>
        get() = HomeLayoutCompatW600dpImpl(binding)

    override val w1240dpImpl: BaseLayoutCompat<*>
        get() = HomeLayoutCompatW1240dpImpl(binding)

    private class HomeLayoutCompatImpl(binding: FragmentHomeBinding): HomeLayoutCompat(binding)

    private class HomeLayoutCompatW600dpImpl(binding: FragmentHomeBinding): HomeLayoutCompat(binding)

    private class HomeLayoutCompatW1240dpImpl(binding: FragmentHomeBinding): HomeLayoutCompat(binding)

}