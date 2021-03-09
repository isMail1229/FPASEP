package id.asep.fpasep.utils.common

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import id.asep.fpasep.R
import id.asep.fpasep.utils.extension.invisible
import id.asep.fpasep.utils.extension.setImagesToGlide
import id.asep.fpasep.utils.extension.visible

fun Toolbar.setCommonToolbar(
    titleTv: TextView,
    descrTv: TextView,
    titleText: String,
    descrText: String,
    imageView: ImageView
) {
    imageView.invisible()
    titleTv.text = titleText
    descrTv.text = descrText
}

fun Toolbar.setToolbarWithImages(
    titleTv: TextView,
    descrTv: TextView,
    titleText: String,
    descrText: String,
    imageView: ImageView,
    avatarImages: String? = null
) {
    imageView.visible()
    titleTv.text = titleText
    descrTv.text = descrText
    if (avatarImages != null) {
        imageView.setImagesToGlide(avatarImages)
    } else {
        imageView.setImagesToGlide(R.drawable.ic_add_photo)
    }
}
