package projekt.cloud.piece.genshin.elements.fragment.elementalAmount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import projekt.cloud.piece.genshin.elements.MainViewModel
import projekt.cloud.piece.genshin.elements.base.BaseFragment
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat.Companion.layoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentElementalAmountBinding

private typealias ElementalAmountUnitFragmentType = BaseFragment<FragmentElementalAmountBinding, ElementalAmountLayoutCompat>

class ElementalAmountFragment: ElementalAmountUnitFragmentType(FragmentElementalAmountBinding::class.java) {

    override fun onCreateLayoutCompat(binding: FragmentElementalAmountBinding): ElementalAmountLayoutCompat =
        binding.layoutCompat(requireContext())

    override fun onSetupViewData(binding: FragmentElementalAmountBinding) {
        binding.mainViewModel = activityViewModels<MainViewModel>().value
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        layoutCompat.setupElementAmountCountdown(this)
    }

}