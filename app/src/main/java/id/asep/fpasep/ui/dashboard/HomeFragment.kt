package id.asep.fpasep.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.asep.fpasep.R
import id.asep.fpasep.databinding.HomeFragmentBinding
import id.asep.fpasep.ui.MainActivity
import id.asep.fpasep.utils.extension.setTitle

class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(
            MainActivity.DASHBOARD_TOOLBAR,
            getString(R.string.home_title),
            getString(R.string.home_description)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}