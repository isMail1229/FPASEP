package id.asep.fpasep.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.asep.fpasep.databinding.AccountFragmentBinding
import id.asep.fpasep.ui.MainActivity
import id.asep.fpasep.utils.extension.setTitle

class AccountFragment : Fragment() {

    private var _binding: AccountFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AccountFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(MainActivity.WITHOUT_TOOLBAR, "", "")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}