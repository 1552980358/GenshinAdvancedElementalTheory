package projekt.cloud.piece.genshin.elements.fragment.elementalAmount

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import projekt.cloud.piece.genshin.elements.R
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat
import projekt.cloud.piece.genshin.elements.databinding.ElementalAmountDecayBinding
import projekt.cloud.piece.genshin.elements.databinding.FragmentElementalAmountBinding
import projekt.cloud.piece.genshin.elements.util.CoroutineUtil.default
import projekt.cloud.piece.genshin.elements.util.CoroutineUtil.main

open class ElementalAmountLayoutCompat: BaseLayoutCompat<FragmentElementalAmountBinding> {

    private companion object {
        const val DECAY_DELAY_INTERVAL = 100L
        const val DECAY_DELAY_RESET = 1000L
        const val DECAY_DELAY_COMPLETE = 2000L
        const val DECAY_REPEAT = 212
        const val DECAY_MIN_ELEMENT_AMOUNT = 0
    }

    constructor(): super(null)
    constructor(binding: FragmentElementalAmountBinding): super(binding)

    override fun getCompatImpl(binding: FragmentElementalAmountBinding): ElementalAmountLayoutCompat =
        CompatImpl(binding)

    override fun getW600dpImpl(binding: FragmentElementalAmountBinding): ElementalAmountLayoutCompat =
        W600dpImpl(binding)

    override fun getW1240dpImpl(binding: FragmentElementalAmountBinding): ElementalAmountLayoutCompat =
        W1240dpImpl(binding)

    private class CompatImpl(binding: FragmentElementalAmountBinding): ElementalAmountLayoutCompat(binding)

    private class W600dpImpl(binding: FragmentElementalAmountBinding): ElementalAmountLayoutCompat(binding)

    private class W1240dpImpl(binding: FragmentElementalAmountBinding): ElementalAmountLayoutCompat(binding)

    private val decay: ElementalAmountDecayBinding
        get() = binding.elementalAmountDecay
    private val weak: LinearProgressIndicator
        get() = decay.linearProgressIndicatorWeak
    private val strong: LinearProgressIndicator
        get() = decay.linearProgressIndicatorStrong
    private val `super`: LinearProgressIndicator
        get() = decay.linearProgressIndicatorSuper

    private var job: Job? = null

    fun setupElementAmountCountdown(fragment: Fragment) {
        beginDecayCountdown(fragment.lifecycleScope, fragment.resources)
    }

    private fun beginDecayCountdown(coroutineScope: CoroutineScope, resources: Resources) {
        job?.cancel()
        job = coroutineScope.default {
            val weakMax = resources.getInteger(R.integer.element_amount_count_down_weak)
            val strongMax = resources.getInteger(R.integer.element_amount_count_down_strong)
            val superMax = resources.getInteger(R.integer.element_amount_count_down_super)

            val weakDecay = resources.getInteger(R.integer.element_decay_weak)
            val strongDecay = resources.getInteger(R.integer.element_decay_strong)
            val superDecay = resources.getInteger(R.integer.element_decay_super)

            var weakAmount: Int
            var strongAmount: Int
            var superAmount: Int

            delay(DECAY_DELAY_COMPLETE)

            while (true) {
                repeat(DECAY_REPEAT) { count ->
                    delay(DECAY_DELAY_INTERVAL)

                    decay.timer = count / 10

                    weakAmount = getEnergyUnit(weakMax, weakDecay, count)
                    decay.weakAmount = weakAmount

                    strongAmount = getEnergyUnit(strongMax, strongDecay, count)
                    decay.strongAmount = strongAmount

                    superAmount = getEnergyUnit(superMax, superDecay, count)
                    decay.superAmount = superAmount

                    main {
                        weak.setProgressCompat(weakAmount, true)
                        strong.setProgressCompat(strongAmount, true)
                        `super`.setProgressCompat(superAmount, true)
                    }

                    if (count == 0) {
                        delay(DECAY_DELAY_RESET)
                    }
                }

                delay(DECAY_DELAY_COMPLETE)
            }
        }
    }

    private fun getEnergyUnit(max: Int, decay: Int, repeat: Int) =
        (max - repeat * decay).takeIf { it > DECAY_MIN_ELEMENT_AMOUNT } ?: DECAY_MIN_ELEMENT_AMOUNT

    override fun close() {
        job?.cancel()
        super.close()
    }

}