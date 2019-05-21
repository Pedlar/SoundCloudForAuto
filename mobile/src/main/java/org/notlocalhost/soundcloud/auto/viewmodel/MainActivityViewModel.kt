package org.notlocalhost.soundcloud.auto.viewmodel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.notlocalhost.soundcloud.auto.api.ScApi
import org.notlocalhost.soundcloud.auto.datastores.TokenDataStore
import org.notlocalhost.soundcloud.auto.di.scopes.ActivityScope
import org.notlocalhost.soundcloud.auto.fragments.HomeFragment
import org.notlocalhost.soundcloud.auto.fragments.LoginFragment
import javax.inject.Inject

@ActivityScope
class MainActivityViewModel @Inject constructor(
    val scApi: ScApi,
    val tokenDataStore: TokenDataStore,
    val homeFragment: HomeFragment,
    val loginFragment: LoginFragment) {

    fun getFragmentToDisplay() = when(scApi.isLoggedIn()) {
        true -> homeFragment
        false -> loginFragment
    }

    fun observeForTokenChanges() = tokenDataStore
        .observeTokenChanges()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}