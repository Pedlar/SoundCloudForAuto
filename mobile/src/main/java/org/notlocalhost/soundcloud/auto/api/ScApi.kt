package org.notlocalhost.soundcloud.auto.api

import android.net.Uri
import com.google.gson.Gson
import com.soundcloud.api.ApiWrapper
import com.soundcloud.api.Request
import com.soundcloud.api.Token
import io.reactivex.Observable
import io.reactivex.Single
import org.notlocalhost.soundcloud.auto.api.v2.v2Service
import javax.inject.Inject
import org.notlocalhost.soundcloud.auto.models.LoginModel
import org.notlocalhost.soundcloud.auto.models.SoundCloudUser

/**
 * This is a combination of the legacy java api wrapper, to get login info / token and local user info
 * and some calls to api-v2 to get reposted songs, api-v2 isn't public, this is subject to break in the future. Or just stop working entirely.
 *
 * TODO: Only does 2 pages right now, need to expand on how paging the API will work with their href
 */
class ScApi @Inject constructor(val apiWrapper: ApiWrapper, val v2Service: v2Service, val gson: Gson) {

    private var storedOffset: String = "0"

    fun isLoggedIn() = apiWrapper.token?.valid() ?: false

    fun login(loginModel: LoginModel) = Single.create<Token> { emitter ->
        val token = apiWrapper.login(loginModel.username, loginModel.password, Token.SCOPE_NON_EXPIRING)
        emitter.onSuccess(token)
    }

    fun getMe() = Single.create<SoundCloudUser> { emitter ->
        val responseString = apiWrapper.get(Request.to("/me"))
            .entity
            .content
            .bufferedReader()
            .use {
                it.readText()
            }
        val soundCloudUser = gson.fromJson(responseString, SoundCloudUser::class.java)
        emitter.onSuccess(soundCloudUser)
    }

    fun getReposts(userId: Int, nextPage: Boolean = false) =
        run {
            if(nextPage)
                v2Service.getReposts(userId, offset = storedOffset)
            else
                v2Service.getReposts(userId)
        }.flatMap { repostWrapper ->
            storedOffset = Uri.parse(repostWrapper.nextHref).getQueryParameter("offset") ?: "0"
            Observable.fromArray(repostWrapper.collection)
        }
}