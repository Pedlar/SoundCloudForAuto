package org.notlocalhost.soundcloud.auto.di.binding

import org.notlocalhost.soundcloud.auto.di.scopes.ServiceScope

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.notlocalhost.soundcloud.auto.di.modules.services.MusicServiceModule
import org.notlocalhost.soundcloud.auto.services.MyMusicService

@Module
internal abstract class ServiceBindingModule {

    @ContributesAndroidInjector(modules = [MusicServiceModule::class])
    @ServiceScope
    abstract fun contributeMusicService(): MyMusicService

}