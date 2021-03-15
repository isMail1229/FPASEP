package id.asep.fpasep.ui.authentication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.asep.fpasep.datasource.remote.models.user.UserLoginRequest
import id.asep.fpasep.datasource.repository.user.UserRepository
import id.asep.fpasep.datasource.repository.user.auth.UserAuthentication
import id.asep.fpasep.ui.authentication.viewmodel.state.LoginError
import id.asep.fpasep.ui.authentication.viewmodel.state.LoginState
import id.asep.fpasep.ui.authentication.viewmodel.state.LoginSuccess
import id.asep.fpasep.utils.common.NetworkError
import id.asep.fpasep.utils.common.NetworkIdle
import id.asep.fpasep.utils.common.NetworkLoading
import id.asep.fpasep.utils.common.NetworkState
import id.asep.fpasep.utils.exception.AuthFailedException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    application: Application,
) : AndroidViewModel(application) {

    private val loginLiveData = MutableLiveData<LoginState>()
    private val networkState = MutableLiveData<NetworkState>()

    var email: String = ""
    var password: String = ""

    fun getLoginState(): LiveData<LoginState> = loginLiveData

    fun getNetworkState(): LiveData<NetworkState> = networkState

    fun login(email: String, password: String) {
        val userCredential = UserLoginRequest(
            email, password
        )

        networkState.value = NetworkLoading
        val authCallback = object : UserAuthentication.AuthCallBack {
            override fun onSuccess() {
                networkState.value = NetworkIdle
                loginLiveData.value = LoginSuccess
            }

            override fun onFailure(e: AuthFailedException) {
                networkState.value = NetworkError(e.message ?: "")
                loginLiveData.value = LoginError(e.message ?: "")
            }
        }
        userRepository.login(userCredential, authCallback)
    }
}
