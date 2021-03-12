package id.asep.fpasep.utils.exception

class ExceptionHandler : Exception {
    var errorCode = 0
        private set

    constructor(errorCode: Int, message: String) : super(message) {
        this.errorCode = errorCode
    }

    constructor(errorCode: Int, message: String, cause: Throwable) : super(message, cause) {
        this.errorCode = errorCode
    }
}