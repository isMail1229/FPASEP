package id.asep.fpasep.utils.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieAnimationView
import id.asep.fpasep.R
import id.asep.fpasep.databinding.DialogLayoutBinding
import id.asep.fpasep.ui.authentication.domain.DialogDomain
import id.asep.fpasep.utils.extension.invisible
import id.asep.fpasep.utils.extension.navigateTo
import id.asep.fpasep.utils.extension.visible

class CustomDialog : DialogFragment() {

    private var _binding: DialogLayoutBinding? = null
    private val binding
        get() = _binding!!

    private val args by navArgs<CustomDialogArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = args.dialogArgs

        when (args.lottieState) {
            ERROR_STATE -> {
                setDataToDialog(args)
                binding.lottieAnimationSuccess.invisible()
                binding.lottieAnimationError.visible()
                startAnimation(binding.lottieAnimationError)
            }

            SUCCESS_STATE -> {
                setDataToDialog(args)
                binding.lottieAnimationError.invisible()
                binding.lottieAnimationSuccess.visible()
                startAnimation(binding.lottieAnimationSuccess)
            }
        }

        binding.btnDialog.setOnClickListener {
            when (args.lottieState) {
                SUCCESS_STATE -> {
                    val action = CustomDialogDirections.actionCustomDialogToMainMenuFragment()
                    navigateTo(action)
                }
                ERROR_STATE -> {
                    dismiss()
                }
            }
        }
    }

    private fun startAnimation(lottieAnimationView: LottieAnimationView) {
        lottieAnimationView.apply {
            playAnimation()
        }
    }

    private fun setDataToDialog(args: DialogDomain) {
        binding.titleDialog.text = args.title
        binding.descriptionDialog.text = args.description
        binding.btnDialog.text = args.buttonText
    }

    override fun onStart() {
        super.onStart()
        val dialogFragment = dialog
        if (dialogFragment != null) {
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            dialogFragment.window?.setLayout(width, height)
            dialogFragment.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_margin)
        }
    }

    companion object {
        const val ERROR_STATE = 1
        const val SUCCESS_STATE = 2
    }

}