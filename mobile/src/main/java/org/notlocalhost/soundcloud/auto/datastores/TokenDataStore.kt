package org.notlocalhost.soundcloud.auto.datastores

import android.content.SharedPreferences
import com.soundcloud.api.Token
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenDataStore @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private var tokenUpdatedSubject = BehaviorSubject.create<Token>()

    fun observeTokenChanges() = tokenUpdatedSubject as Observable<Token>

    var token: Token?
    get() = with(sharedPreferences) {
        if (contains(TOKEN_ACCESS_KEY))
            Token(getString(TOKEN_ACCESS_KEY, null),
                  getString(TOKEN_REFRESH_KEY, null),
                  getString(TOKEN_SCOPE_KEY, null))
        else
            null
    }
    set(value) = with(sharedPreferences.edit()) {
        putString(TOKEN_ACCESS_KEY, value?.access)
        putString(TOKEN_REFRESH_KEY, value?.refresh)
        putString(TOKEN_SCOPE_KEY, value?.scope)
        apply()

        value?.run {
            tokenUpdatedSubject.onNext(value)
        }
    }

    companion object {
        const val TOKEN_ACCESS_KEY = "token_access_key"
        const val TOKEN_REFRESH_KEY = "token_refresh_key"
        const val TOKEN_SCOPE_KEY = "token_scope_key"
    }
}