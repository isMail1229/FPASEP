package id.asep.fpasep.utils.extension

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import id.asep.fpasep.R
import pub.devrel.easypermissions.EasyPermissions

inline fun <reified VM : ViewModel> viewModels(owner: ViewModelStoreOwner) =
    ViewModelProvider(owner).get(VM::class.java)

inline fun <reified VM : ViewModel> Fragment.fragmentViewModel(provider: ViewModelProvider.Factory) =
    ViewModelProvider(this, provider).get(VM::class.java)

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
