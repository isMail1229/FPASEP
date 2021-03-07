package id.asep.fpasep.utils.extension

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import timber.log.Timber


fun NavController.navigateSafe(directions: NavDirections) {
    try {
        navigate(directions)
    } catch (ex: IllegalArgumentException) {
        Timber.e(ex)
    }
}
