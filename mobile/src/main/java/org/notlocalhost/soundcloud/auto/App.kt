package org.notlocalhost.soundcloud.auto

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import org.notlocalhost.soundcloud.auto.di.components.DaggerAppComponent
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var  dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(application = this)
    }

    override fun activityInjector() = this.dispatchingActivityInjector

}