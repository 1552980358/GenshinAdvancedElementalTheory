package projekt.cloud.piece.genshin.elements.fragment.navigation

import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentNavigationBinding

open class NavigationLayoutCompat(
    binding: FragmentNavigationBinding
): BaseLayoutCompat<FragmentNavigationBinding>(binding) {

    override val compatImpl: NavigationLayoutCompat
        get() = CompatImpl(binding)

    override val w600dpImpl: NavigationLayoutCompat
        get() = W600dpImpl(binding)

    override val w1240dpImpl: NavigationLayoutCompat
        get() = W1240dpImpl(binding)

    private class CompatImpl(binding: FragmentNavigationBinding): NavigationLayoutCompat(binding)

    private class W600dpImpl(binding: FragmentNavigationBinding): NavigationLayoutCompat(binding)

    private class W1240dpImpl(binding: FragmentNavigationBinding): NavigationLayoutCompat(binding)

}