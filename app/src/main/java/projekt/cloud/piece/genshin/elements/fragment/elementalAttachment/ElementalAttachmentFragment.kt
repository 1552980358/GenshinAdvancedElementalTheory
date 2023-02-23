package projekt.cloud.piece.genshin.elements.fragment.elementalAttachment

import projekt.cloud.piece.genshin.elements.base.BaseFragment
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat.Companion.layoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentElementalAttachmentBinding

private typealias BaseElementalAttachmentFragment =
        BaseFragment<FragmentElementalAttachmentBinding, ElementalAttachmentLayoutCompat>

class ElementalAttachmentFragment: BaseElementalAttachmentFragment() {

    override val viewBindingClass: Class<FragmentElementalAttachmentBinding>
        get() = FragmentElementalAttachmentBinding::class.java

    override fun onCreateLayoutCompat(
        binding: FragmentElementalAttachmentBinding
    ): ElementalAttachmentLayoutCompat = binding.layoutCompat(requireContext())

}