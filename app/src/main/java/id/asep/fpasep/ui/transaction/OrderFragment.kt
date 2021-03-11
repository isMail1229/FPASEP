package id.asep.fpasep.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.asep.fpasep.R
import id.asep.fpasep.databinding.OrderFragmentBinding
import id.asep.fpasep.ui.MainActivity
import id.asep.fpasep.utils.extension.setTitle

class OrderFragment : Fragment() {

    private var _binding: OrderFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OrderFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(
            MainActivity.COMMON_TOOLBAR,
            getString(R.string.order_title),
            getString(R.string.order_description),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}