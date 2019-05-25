package org.notlocalhost.soundcloud.auto

import android.app.Activity
import android.app.Application
import android.app.Service
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import org.notlocalhost.soundcloud.auto.di.components.DaggerAppComponent
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>


    private val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(application = this)
    }

    override fun activityInjector() = dispatchingActivityInjector

    override fun serviceInjector() = dispatchingServiceInjector
}