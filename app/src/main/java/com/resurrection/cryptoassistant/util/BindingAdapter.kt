package com.resurrection.cryptoassistant.util

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.resurrection.cryptoassistant.R


@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImage(imageUrl: String?) {
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.setArrowDimensions(30f, 30f)
    circularProgressDrawable.start()

    imageUrl?.let {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(25))
        Glide.with(this)
            .load(imageUrl).override(750,750)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.image_not_found)  // any image in case of error
            .apply(requestOptions)
            .into(this)
    }
}

@BindingAdapter("setArrowBackground")
fun ImageView.setBackground(number: Double) {
    this.setBackgroundResource(if (number > 0) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
}

@BindingAdapter("setCoinPriceBackground")
fun TextView.setCryptoPriceBackground(number: Double) {
    this.setBackgroundResource(if (number > 0) R.drawable.coin_price_up_bg else R.drawable.coin_price_up_down)
}

@BindingAdapter("afterTextChanged")
fun EditText.afterEditTextChanged(onClick: () -> Unit) {
    this.doAfterTextChanged {
        onClick.invoke()
    }
    return
}
