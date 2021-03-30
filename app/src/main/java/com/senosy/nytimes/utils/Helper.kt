package com.senosy.nytimes.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.senosy.nytimes.R
import com.senosy.nytimes.network.models.Result
import com.senosy.nytimes.ui.home.ArticleAdapter
import java.text.SimpleDateFormat
import java.util.*


fun showToast(context: Context?, msg: String?) {
    if (context != null && msg.isNullOrBlank()) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}

fun showToast(context: Context?, msgId: Int?) {
    if (context != null && msgId != null && msgId != 0)
        Toast.makeText(context, msgId, Toast.LENGTH_LONG).show()
}

/**
 * Function to get current date and time
 */
fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("HH:mm dd MMM", Locale.getDefault())
    return sdf.format(Date())
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Result>?) {
    val adapter = recyclerView.adapter as ArticleAdapter
    adapter.submitList(data)
}
@BindingAdapter("articleApiStatus")
fun bindStatus(statusImageView: ImageView, status: RequestStatus?) {
    when (status) {
        RequestStatus.Loading -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        RequestStatus.Fail -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        RequestStatus.Success -> {
            statusImageView.visibility = View.GONE
        }
    }
}
enum class RequestStatus{
    Loading,
    Success,
    Fail
}