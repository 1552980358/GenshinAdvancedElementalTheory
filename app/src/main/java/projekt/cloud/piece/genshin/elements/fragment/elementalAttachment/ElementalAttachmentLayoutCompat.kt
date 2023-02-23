package projekt.cloud.piece.genshin.elements.fragment.elementalAttachment

import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat
import projekt.cloud.piece.genshin.elements.databinding.FragmentElementalAttachmentBinding

open class ElementalAttachmentLayoutCompat: BaseLayoutCompat<FragmentElementalAttachmentBinding> {

    constructor(): super(null)
    constructor(binding: FragmentElementalAttachmentBinding): super(binding)

    override fun getCompatImpl(binding: FragmentElementalAttachmentBinding): ElementalAttachmentLayoutCompat =
        CompatImpl(binding)
    override fun getW600dpImpl(binding: FragmentElementalAttachmentBinding): ElementalAttachmentLayoutCompat =
        W600dpImpl(binding)
    override fun getW1240dpImpl(binding: FragmentElementalAttachmentBinding): ElementalAttachmentLayoutCompat =
        W1240dpImpl(binding)

    private class CompatImpl(binding: FragmentElementalAttachmentBinding): ElementalAttachmentLayoutCompat(binding)

    private class W600dpImpl(binding: FragmentElementalAttachmentBinding): ElementalAttachmentLayoutCompat(binding)

    private class W1240dpImpl(binding: FragmentElementalAttachmentBinding): ElementalAttachmentLayoutCompat(binding)

}