package org.notlocalhost.soundcloud.auto.api.modules

import com.google.gson.Gson
import com.soundcloud.api.ApiWrapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.notlocalhost.soundcloud.auto.BuildConfig
import org.notlocalhost.soundcloud.auto.api.v2.ClientIdAuthInterceptor
import org.notlocalhost.soundcloud.auto.api.v2.v2Service
import org.notlocalhost.soundcloud.auto.datastores.TokenDataStore
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class SoundcloudApiModule {

    @Provides @Singleton
    fun providesSoundcloudApiWrapper(tokenDataStore: TokenDataStore) =
        ApiWrapper(BuildConfig.SC_CLIENT_ID, BuildConfig.SC_CLIENT_SECRET, null, tokenDataStore.token)

    @Provides
    fun providesV2Service(@Named("api_v2") retrofit: Retrofit) = retrofit.create(v2Service::class.java)

    @Provides
    @Named("api_v2")
    fun providesRetrofitForV2(gson: Gson, okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("https://api-v2.soundcloud.com")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun providesOkHttpClient(clientIdAuthInterceptor: ClientIdAuthInterceptor) =
            OkHttpClient.Builder()
                .addInterceptor(clientIdAuthInterceptor)
                .build()
}