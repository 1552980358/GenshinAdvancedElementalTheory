package projekt.cloud.piece.genshin.elements.fragment.navigation

import projekt.cloud.piece.genshin.elements.base.BaseFragment
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat.Companion.layoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentNavigationBinding

private typealias BaseNavigationFragment = BaseFragment<FragmentNavigationBinding, NavigationLayoutCompat>

class NavigationFragment: BaseNavigationFragment() {

    override val viewBindingClass: Class<FragmentNavigationBinding>
        get() = FragmentNavigationBinding::class.java

    override fun onCreateLayoutCompat(binding: FragmentNavigationBinding): NavigationLayoutCompat =
        binding.layoutCompat(requireContext())

}