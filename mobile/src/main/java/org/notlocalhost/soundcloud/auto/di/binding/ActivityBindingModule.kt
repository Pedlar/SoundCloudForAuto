package org.notlocalhost.soundcloud.auto.di.binding

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.notlocalhost.soundcloud.auto.MainActivity
import org.notlocalhost.soundcloud.auto.di.scopes.ActivityScope
import org.notlocalhost.soundcloud.auto.di.modules.activities.MainActivityModule

@Module
internal abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    abstract fun contributeMainActivity(): MainActivity

}