package metal.medshrink.network

class SessionsHeaderStorage {

    private var sessionsHeader: String? = null

    fun get(): String? = sessionsHeader

    fun set(value: String?) {
        sessionsHeader = value
    }
}
