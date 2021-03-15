package id.asep.fpasep.ui.authentication.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import id.asep.fpasep.R
import id.asep.fpasep.databinding.RegisterPhotoFragmentBinding
import id.asep.fpasep.ui.MainActivity
import id.asep.fpasep.ui.authentication.domain.RegisterPhotoDomain
import id.asep.fpasep.utils.extension.navigateTo
import id.asep.fpasep.utils.extension.setTitle

class RegisterPhotoFragment : Fragment() {

    private var _binding: RegisterPhotoFragmentBinding? = null
    private val binding
        get() = _binding!!

    private var pathProfileImage: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterPhotoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(
            MainActivity.COMMON_TOOLBAR,
            getString(R.string.sign_up_title),
            getString(R.string.sign_up_description)
        )

        bindButton()
    }

    private fun bindButton() {
        binding.btnSignIn.setOnClickListener {
            processRegister()
        }
    }

    private fun processRegister() {
        val name = binding.etFullName.text?.toString() ?: ""
        val email = binding.etEmailCreate.text?.toString() ?: ""
        val password = binding.etPasswordRegister.text?.toString() ?: ""
        val confirmPassword = binding.etPasswordConfirmRegister.text?.toString() ?: ""
        val imgPath = pathProfileImage

        if (name.isBlank()) showErrorMessage(binding.etFullName)
        if (email.isBlank()) showErrorMessage(binding.etEmailCreate)
        if (password.isBlank()) showErrorMessage(binding.etPasswordRegister)
        if (confirmPassword.isBlank()) showErrorMessage(binding.etPasswordRegister)
        if (confirmPassword != password) showErrorMessage(
            binding.etPasswordConfirmRegister,
            "Confirm password not same with password"
        )

        validateRegister(name, email, password, imgPath, confirmPassword)
    }

    private fun validateRegister(
        name: String,
        email: String,
        password: String,
        imgPath: String,
        confirmPassword: String
    ) {
        if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
            val registerPhotoDomain =
                RegisterPhotoDomain(name, email, password, imgPath, confirmPassword)
            val action =
                RegisterPhotoFragmentDirections.actionRegisterPhotoFragmentToRegisterFragment(
                    registerPhotoDomain
                )
            navigateTo(action)
        }
    }

    private fun showErrorMessage(
        editText: EditText,
        errorMessage: String = "Field cannot be empty"
    ) {
        editText.error = errorMessage
    }

    private fun getImagePath() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}