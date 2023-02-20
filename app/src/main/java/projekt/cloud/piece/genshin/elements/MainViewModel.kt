package projekt.cloud.piece.genshin.elements

import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat.Type
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _systemInsets = MutableLiveData(Rect())
    val systemInsets: LiveData<Rect>
        get() = _systemInsets

    fun obtainSystemInsets(decorView: View) {
        ViewCompat.setOnApplyWindowInsetsListener(decorView) { _, windowInsetsCompat ->
            windowInsetsCompat.getInsets(Type.systemBars()).let { insets ->
                _systemInsets.value?.let { rect ->
                    rect.top = insets.top
                    rect.left = insets.left
                    rect.right = insets.right
                    rect.bottom = insets.bottom
                }
            }
            return@setOnApplyWindowInsetsListener windowInsetsCompat
        }
    }

}