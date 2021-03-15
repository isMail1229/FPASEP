package id.asep.fpasep.ui.authentication.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import id.asep.fpasep.R
import id.asep.fpasep.databinding.RegisterAddressFragmentBinding
import id.asep.fpasep.ui.MainActivity
import id.asep.fpasep.ui.authentication.domain.DialogDomain
import id.asep.fpasep.ui.authentication.viewmodel.RegisterViewModel
import id.asep.fpasep.ui.authentication.viewmodel.state.RegisterState
import id.asep.fpasep.utils.common.CustomDialog
import id.asep.fpasep.utils.extension.*


class RegisterFragment : Fragment() {

    private var _binding: RegisterAddressFragmentBinding? = null
    private val binding
        get() = _binding!!

    private val args by navArgs<RegisterFragmentArgs>()
    private val viewModel: RegisterViewModel by lazy { fragmentViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterAddressFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(
            MainActivity.COMMON_TOOLBAR,
            getString(R.string.address_title),
            getString(R.string.address_description)
        )

        observe(viewModel.isEnableButton) {
            binding.btnSignUp.isEnabled = it
        }

        observe(viewModel.registerState) { registerState ->
            when (registerState) {
                is RegisterState.RegisterSuccess -> {
                    showHideLayout(false)

                    val dialogData = DialogDomain(
                        "Yeayy.. You are Registered",
                        "Now, you can buy anything you want",
                        "Ok",
                        CustomDialog.SUCCESS_STATE
                    )
                    val action =
                        RegisterFragmentDirections.actionRegisterFragmentToCustomDialog(dialogData)
                    navigateTo(action)
                }
                is RegisterState.RegisterLoading -> {
                    showHideLayout(true)
                }
                is RegisterState.RegisterError -> {
                    showHideLayout(false)

                    val dialogData = DialogDomain(
                        "Oops something wrong!! ",
                        "Please register again to make sure",
                        "Try Again",
                        CustomDialog.ERROR_STATE
                    )
                    val action =
                        RegisterFragmentDirections.actionRegisterFragmentToCustomDialog(dialogData)
                    navigateTo(action)
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            processRegister()
        }

        binding.etPhoneNumberRegister.addTextChangedListener(textWatcher)
        binding.etAddressRegister.addTextChangedListener(textWatcher)
        binding.etCityRegister.addTextChangedListener(textWatcher)
        binding.etHouseNumberReg.addTextChangedListener(textWatcher)
    }

    private fun showHideLayout(value: Boolean) {
        if (value) {
            binding.viewLoading.visible()
            binding.loadingAnimation.visible()
            binding.loadingText.visible()
        } else {
            binding.viewLoading.invisible()
            binding.loadingAnimation.invisible()
            binding.loadingText.invisible()
        }
    }

    private fun processRegister() {
        viewModel.processRegisterData(
            viewModel.phoneNumber,
            viewModel.address,
            viewModel.houseNumber,
            viewModel.city,
            args.registerPhotoArgs
        )
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // do nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // do nothing
        }

        override fun afterTextChanged(s: Editable?) {
            when (s.hashCode()) {
                binding.etPhoneNumberRegister.text.hashCode() -> {
                    viewModel.phoneNumber = s.toString()
                }
                binding.etHouseNumberReg.text.hashCode() -> {
                    viewModel.houseNumber = s.toString()
                }
                binding.etAddressRegister.text.hashCode() -> {
                    viewModel.address = s.toString()
                }
                binding.etCityRegister.text.hashCode() -> {
                    viewModel.city = s.toString()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}