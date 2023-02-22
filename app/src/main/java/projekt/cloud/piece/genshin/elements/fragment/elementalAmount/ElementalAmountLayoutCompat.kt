package projekt.cloud.piece.genshin.elements.fragment.elementalAmount

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import projekt.cloud.piece.genshin.elements.base.BaseLayoutCompat
import projekt.cloud.piece.genshin.elements.databinding.ElementalAmountDecayBinding
import projekt.cloud.piece.genshin.elements.databinding.FragmentElementalAmountBinding
import projekt.cloud.piece.genshin.elements.util.CoroutineUtil.default
import projekt.cloud.piece.genshin.elements.util.CoroutineUtil.main
import projekt.cloud.piece.genshin.elements.widget.ValueBar

open class ElementalAmountLayoutCompat: BaseLayoutCompat<FragmentElementalAmountBinding> {

    private companion object {
        const val DECAY_DELAY_INTERVAL = 100L
        const val DECAY_DELAY_RESET = 1000L
        const val DECAY_DELAY_COMPLETE = 2000L
        const val DECAY_REPEAT = 220
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
    private val weak: ValueBar
        get() = decay.valueBarWeak
    private val strong: ValueBar
        get() = decay.valueBarStrong
    private val `super`: ValueBar
        get() = decay.valueBarSuper

    private var job: Job? = null

    fun setupElementAmountCountdown(fragment: Fragment) {
        beginDecayCountdown(fragment.lifecycleScope)
    }

    private fun beginDecayCountdown(coroutineScope: CoroutineScope) {
        job?.cancel()
        job = coroutineScope.default {
            while (true) {
                main {
                    weak.startCountdown()
                    strong.startCountdown()
                    `super`.startCountdown()
                }

                repeat(DECAY_REPEAT) { count ->
                    delay(DECAY_DELAY_INTERVAL)
                    main {
                        decay.timer = count / 10
                        decay.weakAmount = weak.drawValue
                        decay.strongAmount = strong.drawValue
                        decay.superAmount = `super`.drawValue
                    }
                }
                delay(DECAY_DELAY_COMPLETE)

                main {
                    weak.startReset()
                    strong.startReset()
                    `super`.startReset()
                }
                delay(DECAY_DELAY_RESET)
            }
        }
    }

    override fun close() {
        job?.cancel()
        super.close()
    }

}