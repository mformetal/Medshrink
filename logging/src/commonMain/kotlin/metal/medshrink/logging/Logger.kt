package metal.medshrink.logging

import io.github.aakira.napier.Napier

object Logger {

    fun e(exception: Exception, message: String = "") {
        Napier.e(message, exception)
    }
}
