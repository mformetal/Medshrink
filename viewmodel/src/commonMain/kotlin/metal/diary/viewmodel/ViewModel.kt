package metal.diary.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

expect open class ViewModel(dispatcher: CoroutineDispatcher = Dispatchers.Default) {

    val dispatcher: CoroutineDispatcher

    val viewModelScope: CoroutineScope

    open fun onCleared()
}
