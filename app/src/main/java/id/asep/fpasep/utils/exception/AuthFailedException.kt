package id.asep.fpasep.utils.exception

class AuthFailedException : Exception {
    constructor(e: Exception) : super(e)
    constructor(message: String) : super(message)
}