package org.notlocalhost.soundcloud.auto.viewmodel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.notlocalhost.soundcloud.auto.api.ScApi
import org.notlocalhost.soundcloud.auto.datastores.TokenDataStore
import org.notlocalhost.soundcloud.auto.di.scopes.ActivityScope
import org.notlocalhost.soundcloud.auto.models.LoginModel
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(val scApi: ScApi, val tokenDataStore: TokenDataStore) {

    fun login(loginModel: LoginModel) = scApi.login(loginModel)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess {
            tokenDataStore.token = it
        }

}