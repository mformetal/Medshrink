package metal.diary.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

@Suppress("EmptyDefaultConstructor")
actual open class ViewModel actual constructor() : ViewModel() {

    actual val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    public actual override fun onCleared() {
        super.onCleared()

        viewModelScope.cancel()
    }
}