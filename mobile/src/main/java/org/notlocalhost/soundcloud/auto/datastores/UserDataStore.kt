package org.notlocalhost.soundcloud.auto.datastores

import android.content.SharedPreferences
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.notlocalhost.kotlin.extenstions.invoke
import org.notlocalhost.kotlin.extenstions.toMap
import org.notlocalhost.soundcloud.auto.models.SoundCloudUser
import javax.inject.Inject
import javax.inject.Singleton

/*
    TODO: For now this will just be a broken up SharedPrefs, move this into Room? That way we can cache and page from it
     and the network
 */
@Singleton
class UserDataStore @Inject constructor(private val sharedPreferences: SharedPreferences) {
    private var userUpdatedSubject = BehaviorSubject.create<SoundCloudUser>()

    fun observeUserChanges() = userUpdatedSubject as Observable<SoundCloudUser>

    var soundCloudUser: SoundCloudUser?
    get() = with(sharedPreferences) {
        if(contains(ID))
            SoundCloudUser(
                sharedPreferences.all
                    .filterKeys { it.startsWith(USER_PREF) }
                    .mapKeys { it.key.substring(USER_PREF.length) }
                    .mapValues { it.value!! }
            )

        else
            null
    }
    set(value) {
        with(sharedPreferences.edit()) {
            value?.toMap(USER_PREF)
                ?.forEach { (key, value) ->
                    when(value) {
                        is String -> putString(key, value)
                        is Int -> putInt(key, value)
                        is Boolean -> putBoolean(key, value)
                    }
                }

            apply()
        }
        value?.run {
            userUpdatedSubject.onNext(value)
        }
    }

    companion object {
        private const val ID = "user_id"
        private const val USER_PREF = "user_"
    }
}