package metal.diary.multiplatform.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

actual open class ViewModel actual constructor() {

    actual val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    actual open fun onCleared() {
        viewModelScope.cancel()
    }
}