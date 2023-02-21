package projekt.cloud.piece.genshin.elements.fragment.home

import androidx.fragment.app.activityViewModels
import projekt.cloud.piece.genshin.elements.MainViewModel
import projekt.cloud.piece.genshin.elements.base.BaseFragment
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat.Companion.layoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentHomeBinding

private typealias HomeBaseFragment = BaseFragment<FragmentHomeBinding, HomeLayoutCompat>

class HomeFragment: HomeBaseFragment() {

    override val viewBindingClass: Class<FragmentHomeBinding>
        get() = FragmentHomeBinding::class.java

    override fun onCreateLayoutCompat(binding: FragmentHomeBinding): HomeLayoutCompat =
        binding.layoutCompat(requireContext())

    override fun onSetupViewData(binding: FragmentHomeBinding) {
        binding.mainViewModel = activityViewModels<MainViewModel>().value
    }

}