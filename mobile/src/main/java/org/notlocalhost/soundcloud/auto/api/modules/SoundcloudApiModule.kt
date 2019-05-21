package org.notlocalhost.soundcloud.auto.api.modules

import com.soundcloud.api.ApiWrapper
import dagger.Module
import dagger.Provides
import org.notlocalhost.soundcloud.auto.BuildConfig
import org.notlocalhost.soundcloud.auto.datastores.TokenDataStore
import javax.inject.Singleton

@Module
class SoundcloudApiModule {

    @Provides @Singleton
    fun providesSoundcloudApiWrapper(tokenDataStore: TokenDataStore) =
        ApiWrapper(BuildConfig.SC_CLIENT_ID, BuildConfig.SC_CLIENT_SECRET, null, tokenDataStore.token)
}