package projekt.cloud.piece.genshin.elements.fragment.navigation

import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentNavigationBinding

open class NavigationLayoutCompat: BaseLayoutCompat<FragmentNavigationBinding> {

    constructor(): super(null)
    private constructor(binding: FragmentNavigationBinding): super(binding)

    override fun getCompatImpl(binding: FragmentNavigationBinding): NavigationLayoutCompat =
        CompatImpl(binding)

    override fun getW600dpImpl(binding: FragmentNavigationBinding): NavigationLayoutCompat =
        W600dpImpl(binding)

    override fun getW1240dpImpl(binding: FragmentNavigationBinding): NavigationLayoutCompat =
        W1240dpImpl(binding)

    private class CompatImpl(binding: FragmentNavigationBinding): NavigationLayoutCompat(binding)

    private class W600dpImpl(binding: FragmentNavigationBinding): NavigationLayoutCompat(binding)

    private class W1240dpImpl(binding: FragmentNavigationBinding): NavigationLayoutCompat(binding)

}