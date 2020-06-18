package pe.isil.esports.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import coil.Coil
import coil.api.load
import coil.request.LoadRequest

fun Context.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.posterLoading(path: String?, progress: ProgressBar) {
    load(path) {
        crossfade(true)
    }

    val loader = Coil.imageLoader(context)
    val request = LoadRequest.Builder(context)
        .data(path)
        .target(onStart = {
            progress.visibility = View.VISIBLE
        }, onSuccess = {
            progress.visibility = View.GONE
        }, onError = {
            progress.visibility = View.GONE
        }).build()
    loader.execute(request)
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer(observer))
}