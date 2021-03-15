package id.asep.fpasep.utils.extension

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import id.asep.fpasep.R
import id.asep.fpasep.ui.MainActivity
import pub.devrel.easypermissions.EasyPermissions

inline fun <reified VM : ViewModel> Fragment.fragmentViewModel() =
    ViewModelProvider(requireActivity()).get(VM::class.java)

fun <T> Fragment.observe(liveData: LiveData<T>, observer: Observer<T>) {
    liveData.observe(viewLifecycleOwner, observer)
}

fun Fragment.snackbar(text: String) {
    Snackbar.make(
        requireView(),
        text,
        Snackbar.LENGTH_LONG
    ).show()
}

fun Fragment.toastShort(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Fragment.toastLong(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
}

fun Fragment.setTitle(type: Int, title: String, description: String, imgPath: String = "") {
    val act = activity
    if (act is MainActivity) {
        act.setupToolbar(type, title, description, imgPath)
    }
}

/*@Suppress("SpreadOperator")*/
fun Fragment.askPermissions(
    requestCode: Int,
    rationaleMessage: String,
    permissions: Array<String>,
    afterPermissionsGrantedAction: () -> Unit
) {
    val ctx = context ?: return

    if (EasyPermissions.hasPermissions(ctx, *permissions)) {
        afterPermissionsGrantedAction()
    } else {
        EasyPermissions.requestPermissions(
            this,
            rationaleMessage,
            requestCode,
            *permissions
        )
    }
}

fun Fragment.navigateTo(action: NavDirections) {
    findNavController().navigate(action)
}

/*@Suppress("SpreadOperator")*/
fun Fragment.handlePermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray,
    afterPermissionsGrantedAction: () -> Unit
) {
    EasyPermissions.onRequestPermissionsResult(
        requestCode,
        permissions,
        grantResults,
        object : EasyPermissions.PermissionCallbacks {
            override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
                if (EasyPermissions.somePermissionPermanentlyDenied(
                        this@handlePermissionsResult,
                        perms
                    )
                ) {
                    Toast.makeText(
                        context,
                        getString(R.string.access_failed_permission_message),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onRequestPermissionsResult(
                requestCode: Int,
                permissions: Array<out String>,
                grantResults: IntArray
            ) {
                // do nothing
            }

            override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
                afterPermissionsGrantedAction()
            }
        })
}
