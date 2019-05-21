package org.notlocalhost.soundcloud.auto.api

import android.util.Log
import com.soundcloud.api.ApiWrapper
import com.soundcloud.api.Http
import com.soundcloud.api.Request
import com.soundcloud.api.Token
import io.reactivex.Single
import org.apache.http.HttpResponse
import javax.inject.Inject
import org.apache.http.HttpStatus
import org.notlocalhost.soundcloud.auto.models.LoginModel


class ScApi @Inject constructor(val apiWrapper: ApiWrapper) {

    fun isLoggedIn() = apiWrapper.token?.valid() ?: false

    fun login(loginModel: LoginModel) = Single.create<Token> { emitter ->
        val token = apiWrapper.login(loginModel.username, loginModel.password, Token.SCOPE_NON_EXPIRING)
        emitter.onSuccess(token)
    }

    fun getMe() = Single.create<HttpResponse> { emitter ->
        val resp = apiWrapper.get(Request.to("/me"))
        if (resp.statusLine.statusCode == HttpStatus.SC_OK) {
            System.out.println("\n" + Http.formatJSON(Http.getString(resp)))
        }
        Log.i("Madison", resp.statusLine.statusCode.toString())
        emitter.onSuccess(resp)
    }
}