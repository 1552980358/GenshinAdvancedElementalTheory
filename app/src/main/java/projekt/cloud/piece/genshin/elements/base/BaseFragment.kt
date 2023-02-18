package projekt.cloud.piece.genshin.elements.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import projekt.cloud.piece.genshin.elements.util.ViewBindingReflector.LayoutBindingReflectorUtil.inflate

abstract class BaseFragment<VB: ViewBinding>(private val viewBindingClass: Class<VB>): Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = viewBindingClass.inflate(inflater, container)
        return binding.root
    }

}