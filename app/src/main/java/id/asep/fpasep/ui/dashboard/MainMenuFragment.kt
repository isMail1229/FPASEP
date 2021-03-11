package id.asep.fpasep.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import id.asep.fpasep.R
import id.asep.fpasep.databinding.BottomNavigationFragmentBinding
import id.asep.fpasep.ui.MainActivity
import id.asep.fpasep.utils.extension.setTitle

class MainMenuFragment : Fragment() {
    private var _binding: BottomNavigationFragmentBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(
            MainActivity.WITHOUT_TOOLBAR, "", ""
        )
        setupBottomNavigation()

        // This callback will only be called when MyFragment is at least Started.
        val onBacPressCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finishAfterTransition()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), onBacPressCallback)
    }

    private fun setupBottomNavigation() {
        val act = activity
        if (act is MainActivity) {
            val navHostFragment =
                childFragmentManager.findFragmentById(R.id.menu_nav_host_fragment) as NavHostFragment
            act.setupBottomNavigation(binding.bottomNavigationView, navHostFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}