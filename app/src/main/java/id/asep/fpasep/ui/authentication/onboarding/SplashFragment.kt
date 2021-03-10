package id.asep.fpasep.ui.authentication.onboarding

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.asep.fpasep.databinding.SplashFragmentBinding

class SplashFragment : Fragment() {
    private var _binding: SplashFragmentBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAnimation()
    }

    private fun setupAnimation() {
        binding.logoApps.animate().apply {
            alpha(1f)
            duration = DURATION_1000_MILLIS
            startDelay = DURATION_500_MILLIS
            setListener(null)
        }

        binding.textApps.animate().apply {
            alpha(1f)
            duration = DURATION_1000_MILLIS
            startDelay = 500
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    startExitAnimation()
                }
            })
        }

    }

    private fun startExitAnimation() {
        binding.logoApps.animate().apply {
            alpha(0f)
            duration = DURATION_1000_MILLIS
            startDelay = DURATION_500_MILLIS
            setListener(null)
        }

        binding.textApps.animate().apply {
            alpha(0f)
            duration = DURATION_1000_MILLIS
            startDelay = 500
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    openNextFragment()
                }
            })
        }
    }

    private fun openNextFragment() {
        val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val DURATION_500_MILLIS = 500L
        private const val DURATION_1000_MILLIS = 1000L
    }
}