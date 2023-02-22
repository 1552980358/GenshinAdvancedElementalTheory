package projekt.cloud.piece.genshin.elements.widget

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style.FILL
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.View.MeasureSpec.EXACTLY
import android.view.View.MeasureSpec.UNSPECIFIED
import androidx.core.animation.doOnEnd
import kotlin.math.min
import projekt.cloud.piece.genshin.elements.R

private typealias CompleteListener = () -> Unit

class ValueBar(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    private val valueDuration: Long
    private val resetDuration: Long

    private val primaryValue: Int
    private val secondaryValue: Int
    private val maxValue: Int

    private var drawValue: Int

    private val primaryPaint = Paint().apply {
        isAntiAlias = true
        style = FILL
    }
    private val secondaryPaint = Paint().apply {
        isAntiAlias = true
        style = FILL
    }
    private val trackPaint = Paint().apply {
        isAntiAlias = true
        style = FILL
    }

    private var countdownCompleteListener: CompleteListener? = null
    private var resetCompleteListener: CompleteListener? = null

    init {
        context.obtainStyledAttributes(attributeSet, R.styleable.ValueBar).use { typedArray ->
            valueDuration = typedArray.getInt(
                R.styleable.ValueBar_valueDuration,
                resources.getInteger(R.integer.value_bar_value_duration_def)
            ).toLong()
            resetDuration = typedArray.getInt(
                R.styleable.ValueBar_resetDuration,
                resources.getInteger(R.integer.value_bar_reset_duration_def)
            ).toLong()

            maxValue = typedArray.getInt(
                R.styleable.ValueBar_maxValue,
                resources.getInteger(R.integer.value_bar_max_value_def)
            )
            primaryValue = typedArray.getInt(
                R.styleable.ValueBar_primaryValue,
                resources.getInteger(R.integer.value_bar_primary_value_def)
            )
            secondaryValue = typedArray.getInt(
                R.styleable.ValueBar_secondaryValue,
                resources.getInteger(R.integer.value_bar_secondary_value_def)
            )
        }

        drawValue = primaryValue

        TypedValue().let { typedValue ->
            // Primary
            context.theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true)
            primaryPaint.color = typedValue.data

            // Secondary
            context.theme.resolveAttribute(com.google.android.material.R.attr.colorSecondary, typedValue, true)
            secondaryPaint.color = typedValue.data

            // Track
            context.theme.resolveAttribute(com.google.android.material.R.attr.colorSurfaceVariant, typedValue, true)
            trackPaint.color = typedValue.data
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec)
        if (heightMeasureMode == EXACTLY) {
            return super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
        setMeasuredDimension(
            MeasureSpec.getSize(widthMeasureSpec),
            resources.getDimensionPixelSize(R.dimen.value_bar_height_default).let { height ->
                if (heightMeasureMode == UNSPECIFIED) { height }
                else { min(MeasureSpec.getSize(heightMeasureSpec), height) }
            }
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        val widthF = width.toFloat()
        val heightF = height.toFloat()

        // Track
        canvas.drawRect(0F, 0F, widthF, heightF, trackPaint)

        // Secondary
        if (secondaryValue > drawValue) {
            canvas.drawRect(0F, 0F, widthF * secondaryValue / maxValue, heightF, secondaryPaint)
        }

        // Primary
        if (drawValue > 0) {
            canvas.drawRect(0F, 0F, widthF * drawValue / maxValue, heightF, primaryPaint)
        }
    }

    private var animator: Animator? = null

    fun startCountdown() {
        animator?.cancel()
        animator = ValueAnimator.ofInt(primaryValue, 0)
            .setDuration(valueDuration)
            .apply {
                addUpdateListener {
                    drawValue = it.animatedValue as Int
                    invalidate()
                }
                doOnEnd {
                    animator = null
                    countdownCompleteListener?.invoke()
                }
            }
        animator?.start()
    }

    fun startReset() {
        animator?.cancel()
        animator = ValueAnimator.ofInt(drawValue, primaryValue)
            .setDuration(resetDuration)
            .apply {
                addUpdateListener {
                    drawValue = it.animatedValue as Int
                    invalidate()
                }
                doOnEnd {
                    animator = null
                    resetCompleteListener?.invoke()
                }
            }
        animator?.start()
    }

    fun addListener(onCompleteCountdown: CompleteListener, onCompleteReset: CompleteListener) {
        this.countdownCompleteListener = onCompleteCountdown
        this.resetCompleteListener = onCompleteReset
    }

}