package pe.isil.esports.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import coil.api.load
import com.google.android.material.textfield.TextInputEditText

fun Context.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun TextInputEditText.validate(): Boolean {
    val text = "$text".trim()
    return text.isNotEmpty()
}

fun ImageView.loading(path: String?, progress: View) {
    load(path) {
        target(
            onStart = {
                progress.visibility = View.VISIBLE
            },
            onSuccess = {
                progress.visibility = View.GONE
                this@loading.setImageDrawable(it)
            },
            onError = {
                progress.visibility = View.VISIBLE
            })
        crossfade(true)
    }
}

fun ImageView.icon(drawable: Int) {
    load(drawable) {
        crossfade(true)
    }
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer(observer))
}