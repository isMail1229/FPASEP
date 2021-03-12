package id.asep.fpasep.ui.authentication.domain

data class UserDomain(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val address: String,
    val city: String,
    val houseNumber: String,
    val phoneNumber: String
)
