package pe.isil.esports.data.source.remote.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import pe.isil.esports.data.source.preferences.SessionManager

class AuthInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        SessionManager.fetchToken(context)?.let { 
            request.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(request.build())
    }
}