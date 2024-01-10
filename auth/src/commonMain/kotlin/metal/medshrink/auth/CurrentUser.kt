package metal.medshrink.auth

expect class CurrentUser {

    val exists: Boolean

    operator fun invoke(): User?
}
