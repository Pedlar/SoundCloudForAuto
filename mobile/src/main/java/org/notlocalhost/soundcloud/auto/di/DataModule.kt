package org.notlocalhost.soundcloud.auto.di

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import org.notlocalhost.soundcloud.auto.api.modules.SoundcloudApiModule
import javax.inject.Singleton

@Module(includes = [
    SoundcloudApiModule::class
])
class DataModule {

    @Provides
    @Singleton
    internal fun providesGson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
    internal fun providesSharedPreferences(application: Application) =
        application.getSharedPreferences(MAIN_SHARED_PREFS, Context.MODE_PRIVATE)

    companion object {
        const val MAIN_SHARED_PREFS = "main_shared_prefs"
    }
}