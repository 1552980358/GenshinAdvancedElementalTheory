package projekt.cloud.piece.genshin.elements.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import projekt.cloud.piece.genshin.elements.util.FragmentLifecycleUtil.clearOnViewDestroy
import projekt.cloud.piece.genshin.elements.util.FragmentLifecycleUtil.closeOnViewDestroy
import projekt.cloud.piece.genshin.elements.util.ViewBindingReflector.LayoutBindingReflectorUtil.inflate

abstract class BaseFragment<out VB: ViewBinding, out LC: BaseLayoutCompat<VB>>(
    private val viewBindingClass: Class<VB>
): Fragment() {

    protected var binding: @UnsafeVariance VB by clearOnViewDestroy()
        private set

    protected var layoutCompat: @UnsafeVariance LC by closeOnViewDestroy()
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = viewBindingClass.inflate(inflater, container)
        layoutCompat = onCreateLayoutCompat(binding)
        return binding.root
    }

    protected abstract fun onCreateLayoutCompat(binding: @UnsafeVariance VB): LC

}