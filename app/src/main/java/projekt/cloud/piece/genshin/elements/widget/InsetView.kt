package projekt.cloud.piece.genshin.elements.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.MeasureSpec.UNSPECIFIED
import androidx.databinding.BindingAdapter
import java.lang.Integer.min

class InsetView(context: Context, attributeSet: AttributeSet?): View(context, attributeSet) {

    companion object InsetViewUtil {

        private const val INSET_DEFAULT = 0

        @JvmStatic
        @BindingAdapter("inset_horizontal")
        fun InsetView.setInsetHorizontal(insetHorizontal: Int) {
            this.insetHorizontal = insetHorizontal
        }

        @JvmStatic
        @BindingAdapter("inset_vertical")
        fun InsetView.setInsetVertical(insetVertical: Int) {
            this.insetVertical = insetVertical
        }

    }

    private var insetHorizontal = INSET_DEFAULT
        set(value) {
            if (field != value) {
                field = value
                requestLayout()
            }
        }

    private var insetVertical = INSET_DEFAULT
        set(value) {
            if (field != value) {
                field = value
                requestLayout()
            }
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (insetHorizontal == INSET_DEFAULT && insetVertical == INSET_DEFAULT) {
            return super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
        setMeasuredDimension(
            when (MeasureSpec.getMode(widthMeasureSpec)) {
                UNSPECIFIED -> insetHorizontal
                else -> min(insetHorizontal, MeasureSpec.getSize(widthMeasureSpec))
            },
            when (MeasureSpec.getMode(heightMeasureSpec)) {
                UNSPECIFIED -> insetVertical
                else -> min(insetVertical, MeasureSpec.getSize(heightMeasureSpec))
            }
        )
    }

}