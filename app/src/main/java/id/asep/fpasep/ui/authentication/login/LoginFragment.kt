package id.asep.fpasep.ui.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.asep.fpasep.R
import id.asep.fpasep.databinding.LoginFragmentBinding
import id.asep.fpasep.ui.MainActivity
import id.asep.fpasep.ui.authentication.domain.DialogDomain
import id.asep.fpasep.ui.authentication.viewmodel.LoginViewModel
import id.asep.fpasep.ui.authentication.viewmodel.state.LoginError
import id.asep.fpasep.ui.authentication.viewmodel.state.LoginSuccess
import id.asep.fpasep.utils.common.CustomDialog
import id.asep.fpasep.utils.common.NetworkError
import id.asep.fpasep.utils.common.NetworkIdle
import id.asep.fpasep.utils.common.NetworkLoading
import id.asep.fpasep.utils.extension.fragmentViewModel
import id.asep.fpasep.utils.extension.navigateTo
import id.asep.fpasep.utils.extension.observe
import id.asep.fpasep.utils.extension.setTitle

class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: LoginViewModel by lazy { fragmentViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(
            MainActivity.COMMON_TOOLBAR,
            getString(R.string.sign_in_title),
            getString(R.string.sign_in_description)
        )

        observerViewModel()
        bindButton()
    }

    private fun observerViewModel() {
        observe(viewModel.getLoginState()) { state ->
            when (state) {
                is LoginError -> {
                    val dataDialog = DialogDomain(
                        "Authentication Failed!!",
                        "Please check email or password again.",
                        "Try Again",
                        CustomDialog.ERROR_STATE
                    )
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToCustomDialog(dataDialog)
                    navigateTo(action)
                }
                is LoginSuccess -> {
                    val dataDialog = DialogDomain(
                        "Authentication Success. Yeayy",
                        "Now, you can make transaction. Happy shopping",
                        "Ok",
                        CustomDialog.SUCCESS_STATE
                    )
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToCustomDialog(dataDialog)
                    navigateTo(action)
                }
            }
        }

        observe(viewModel.getNetworkState()) { state ->
            when (state) {
                is NetworkIdle -> {

                }
                is NetworkLoading -> {

                }
                is NetworkError -> {

                }
            }
        }
    }

    private fun bindButton() {
        binding.btnCreateAccoount.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterPhotoFragment()
            navigateTo(action)
        }
        binding.btnSignIn.setOnClickListener {
            setupLoginCredentials()
        }
    }

    private fun signIndProcess() {
        if (viewModel.email.isBlank()) binding.etEmail.error = "Fill the column"
        if (viewModel.password.isBlank()) binding.etPassword.error = "Fill the column"

        if (viewModel.email.isNotBlank() && viewModel.password.isNotBlank()) {
            viewModel.login(viewModel.email, viewModel.password)
        }
    }

    private fun setupLoginCredentials() {
        viewModel.email = binding.etEmail.text?.toString() ?: ""
        viewModel.password = binding.etPassword.text?.toString() ?: ""

        signIndProcess()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}