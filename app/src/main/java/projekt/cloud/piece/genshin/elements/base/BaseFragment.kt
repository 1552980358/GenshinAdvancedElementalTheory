package projekt.cloud.piece.genshin.elements.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import projekt.cloud.piece.genshin.elements.util.FragmentLifecycleUtil.clearOnViewDestroy
import projekt.cloud.piece.genshin.elements.util.FragmentLifecycleUtil.closeOnViewDestroy
import projekt.cloud.piece.genshin.elements.util.ViewBindingReflector.LayoutBindingReflectorUtil.inflate

abstract class BaseFragment<VB: ViewBinding, LC: BaseLayoutCompat<VB>>: Fragment() {

    protected abstract val viewBindingClass: Class<VB>

    protected var binding: VB by clearOnViewDestroy()
        private set

    protected var layoutCompat:LC by closeOnViewDestroy()
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = viewBindingClass.inflate<VB>(inflater, container).also { viewBinding ->
            if (viewBinding is ViewDataBinding) {
                viewBinding.lifecycleOwner = viewLifecycleOwner
                onSetupViewData(viewBinding)
            }
        }
        layoutCompat = onCreateLayoutCompat(binding)
        return binding.root
    }

    protected abstract fun onCreateLayoutCompat(binding: VB): LC

    protected open fun onSetupViewData(binding: VB) = Unit

}