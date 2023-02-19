package projekt.cloud.piece.genshin.elements.fragment.navigation

import projekt.cloud.piece.genshin.elements.base.BaseFragment
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat.Companion.layoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentNavigationBinding

class NavigationFragment: BaseFragment<FragmentNavigationBinding, NavigationLayoutCompat>(FragmentNavigationBinding::class.java) {

    override fun onCreateLayoutCompat(binding: FragmentNavigationBinding): NavigationLayoutCompat =
        binding.layoutCompat(requireContext())

}