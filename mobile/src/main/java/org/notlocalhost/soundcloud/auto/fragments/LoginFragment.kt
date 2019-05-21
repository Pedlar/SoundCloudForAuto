package org.notlocalhost.soundcloud.auto.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.soundcloud.api.Token
import com.uber.autodispose.AutoDispose.autoDisposable
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import kotlinx.android.synthetic.main.fragment_login.view.*
import org.notlocalhost.soundcloud.auto.models.LoginModel

import org.notlocalhost.soundcloud.auto.R
import org.notlocalhost.soundcloud.auto.boilerplates.BaseFragment
import org.notlocalhost.soundcloud.auto.di.scopes.ActivityScope
import org.notlocalhost.soundcloud.auto.viewmodel.LoginViewModel
import javax.inject.Inject

@ActivityScope
class LoginFragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.submit.setOnClickListener {
            onSubmit(
                LoginModel(
                    view.username.text.toString(),
                    view.password.text.toString()
                )
            )
        }
        return view
    }

    private fun onSubmit(loginModel: LoginModel) {
        viewModel.login(loginModel)
            .`as`(autoDisposable<Token>(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_STOP)))
            .subscribe({ token ->

                }, { throwable ->
                    Log.e("Madison", "Error With Login", throwable)
                })
    }
}
