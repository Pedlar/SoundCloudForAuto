package org.notlocalhost.soundcloud.auto.di.components

import android.app.Application
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.notlocalhost.soundcloud.auto.App
import org.notlocalhost.soundcloud.auto.di.DataModule
import org.notlocalhost.soundcloud.auto.di.binding.ActivityBindingModule
import javax.inject.Singleton
import dagger.BindsInstance
import org.notlocalhost.soundcloud.auto.di.binding.ServiceBindingModule


@Singleton
@Component( modules = [
    DataModule::class,
    ActivityBindingModule::class,
    ServiceBindingModule::class,
    AndroidSupportInjectionModule::class
])

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
    fun inject(application: App)
}