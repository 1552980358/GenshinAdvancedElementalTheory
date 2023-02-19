package projekt.cloud.piece.genshin.elements.fragment.home

import projekt.cloud.piece.genshin.elements.base.BaseFragment
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat.Companion.layoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeLayoutCompat>(FragmentHomeBinding::class.java) {

    override fun onCreateLayoutCompat(binding: FragmentHomeBinding): HomeLayoutCompat =
        binding.layoutCompat(requireContext())

}