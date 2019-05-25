package org.notlocalhost.soundcloud.auto.api.v2

import okhttp3.Interceptor
import okhttp3.Response
import org.notlocalhost.soundcloud.auto.BuildConfig
import javax.inject.Inject

class ClientIdAuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        with (chain.request()) {
            val urlWithClientId = url().newBuilder().addQueryParameter("client_id", BuildConfig.SC_CLIENT_ID).build()
            val newRequest = newBuilder().url(urlWithClientId).build()
            return chain.proceed(newRequest)
        }
}