package id.asep.fpasep.ui.authentication.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.asep.fpasep.R
import id.asep.fpasep.databinding.RegisterAddressFragmentBinding
import id.asep.fpasep.ui.MainActivity
import id.asep.fpasep.utils.extension.setTitle

class RegisterFragment : Fragment() {
    private var _binding: RegisterAddressFragmentBinding? = null
    private val binding
        get() = _binding!!

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

        binding.btnSignIn.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToMainMenuFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}