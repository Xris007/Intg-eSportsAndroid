package pe.isil.esports.data.source.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import pe.isil.esports.BuildConfig

object SessionManager {

    private val PREFERENCES = BuildConfig.APPLICATION_ID

    private val PREFERENCES_TOKEN = "$PREFERENCES.token"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        getSharedPreferences(context).edit(commit = true) {
            putString(PREFERENCES_TOKEN, token)
        }
    }

    fun fetchToken(context: Context): String? {
        return getSharedPreferences(context).getString(PREFERENCES_TOKEN, null)
    }
}