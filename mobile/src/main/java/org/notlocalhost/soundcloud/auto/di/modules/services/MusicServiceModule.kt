package org.notlocalhost.soundcloud.auto.di.modules.services

import androidx.media.MediaBrowserServiceCompat
import dagger.Binds
import dagger.Module
import org.notlocalhost.soundcloud.auto.di.scopes.ServiceScope
import org.notlocalhost.soundcloud.auto.services.MyMusicService

@Module
abstract class MusicServiceModule {

    @Binds
    @ServiceScope
    abstract fun providesMediaBrowserService(target: MyMusicService): MediaBrowserServiceCompat
}