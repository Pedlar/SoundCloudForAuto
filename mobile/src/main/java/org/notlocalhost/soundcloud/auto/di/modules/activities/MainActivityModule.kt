package org.notlocalhost.soundcloud.auto.di.modules.activities

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.notlocalhost.soundcloud.auto.di.scopes.FragmentScope
import org.notlocalhost.soundcloud.auto.fragments.HomeFragment
import org.notlocalhost.soundcloud.auto.fragments.LoginFragment

@Module
abstract class MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}