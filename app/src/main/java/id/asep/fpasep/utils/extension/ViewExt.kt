package id.asep.fpasep.utils.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.asep.fpasep.R


fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun ImageView.setImagesToGlide(pathUrl: String) {
    Glide.with(this.context)
        .load(pathUrl)
        .error(R.drawable.ic_image_error)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.setImagesToGlide(pathDrawable: Int) {
    Glide.with(this.context)
        .load(pathDrawable)
        .error(R.drawable.ic_image_error)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}