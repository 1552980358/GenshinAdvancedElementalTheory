package projekt.cloud.piece.genshin.elements.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import projekt.cloud.piece.genshin.elements.util.ViewBindingReflector.LayoutBindingReflectorUtil.inflate

abstract class BaseFragment<out VB: ViewBinding, out LC: BaseLayoutCompat<VB>>(
    private val viewBindingClass: Class<VB>
): Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    private var _layoutCompat: @UnsafeVariance LC? = null
    protected val layoutCompat: LC
        get() = _layoutCompat!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = viewBindingClass.inflate(inflater, container)
        _layoutCompat = onCreateLayoutCompat(binding)
        return binding.root
    }

    protected abstract fun onCreateLayoutCompat(binding: @UnsafeVariance VB): LC

    override fun onDestroyView() {
        layoutCompat.destroy()
        _layoutCompat = null
        _binding = null
        super.onDestroyView()
    }

}