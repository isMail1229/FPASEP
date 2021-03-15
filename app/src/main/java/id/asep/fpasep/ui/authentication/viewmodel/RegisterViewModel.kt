package id.asep.fpasep.ui.authentication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.asep.fpasep.datasource.repository.user.UserRepository
import id.asep.fpasep.ui.authentication.domain.RegisterPhotoDomain
import id.asep.fpasep.ui.authentication.domain.UserDomain
import id.asep.fpasep.ui.authentication.viewmodel.state.RegisterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _isEnableButton = MutableLiveData(false)
    val isEnableButton: LiveData<Boolean>
        get() = _isEnableButton

    private var _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState>
        get() = _registerState

    var phoneNumber: String = ""
    var houseNumber: String = ""
    var address: String = ""
    var city: String = ""

    fun processRegisterData(
        phoneNumber: String,
        address: String,
        houseNumber: String,
        city: String,
        registerPhotoData: RegisterPhotoDomain
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _registerState.value = RegisterState.RegisterLoading

            val dataUserRegister = UserDomain(
                registerPhotoData.name,
                registerPhotoData.email,
                registerPhotoData.password,
                address,
                city,
                houseNumber,
                phoneNumber,
                registerPhotoData.confirmPassword
            )
            _registerState.value = userRepository.register(dataUserRegister)
        }
    }
}