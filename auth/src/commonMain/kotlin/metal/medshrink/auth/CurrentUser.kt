package metal.medshrink.auth

expect class CurrentUser {

    operator fun invoke(): User?
}