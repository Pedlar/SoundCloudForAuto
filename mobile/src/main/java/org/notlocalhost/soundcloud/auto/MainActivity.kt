package org.notlocalhost.soundcloud.auto

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import org.notlocalhost.soundcloud.auto.boilerplates.BaseActivity
import com.uber.autodispose.AutoDispose.autoDisposable
import org.notlocalhost.soundcloud.auto.fragments.HomeFragment

import org.notlocalhost.soundcloud.auto.viewmodel.MainActivityViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    @Inject
    lateinit var homeFragment: HomeFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        viewModel.observeForUserChanges()
            .`as`(autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_PAUSE)))
            .subscribe({ user ->
                showFragment(homeFragment)
            }, {

            })

        showFragment(viewModel.getFragmentToDisplay())
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
}
