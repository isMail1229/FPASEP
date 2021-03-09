package id.asep.fpasep.ui.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.asep.fpasep.databinding.LoginFragmentBinding
import id.asep.fpasep.ui.MainActivity

class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val act = activity
        if (act is MainActivity) {
            act.showActionBar()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}