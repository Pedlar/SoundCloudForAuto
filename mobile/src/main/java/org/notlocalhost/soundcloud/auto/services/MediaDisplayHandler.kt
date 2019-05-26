package org.notlocalhost.soundcloud.auto.services

import android.net.Uri
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat.MediaItem
import android.support.v4.media.MediaDescriptionCompat
import io.reactivex.Single
import org.notlocalhost.soundcloud.auto.api.ScApi
import org.notlocalhost.soundcloud.auto.datastores.UserDataStore
import org.notlocalhost.soundcloud.auto.models.RepostsCollection
import javax.inject.Inject

class MediaDisplayHandler @Inject constructor(val scApi: ScApi, val userDataStore: UserDataStore) {

    fun handleLoadChildren(parentId: String, options: Bundle): Single<MutableList<MediaItem>> = when(parentId) {
        MEDIA_ROOT_ID -> Single.just(getRootMediaItems())
        MEDIA_REPOSTS_NEXT_PAGE -> scApi.getReposts(userDataStore.soundCloudUser!!.id, nextPage = true)
            .flatMapSingle(flatMapTracksToMediaitemList()).single(ArrayList())
        MEDIA_REPOSTS_ID -> scApi.getReposts(userDataStore.soundCloudUser!!.id)
            .flatMapSingle(flatMapTracksToMediaitemList()).single(ArrayList())
        else -> Single.just(ArrayList())
    }

    private fun flatMapTracksToMediaitemList(): (List<RepostsCollection>) -> Single<MutableList<MediaItem>> {
        return { collection ->
            val trackList = collection.map {
                MediaItem(
                    it.track.toMediaDescription(),
                    MediaItem.FLAG_PLAYABLE
                )
            }.toMutableList()

            trackList.add(
                MediaItem(
                    MediaDescriptionCompat.Builder()
                        .setTitle("Next")
                        .setMediaId(MEDIA_REPOSTS_NEXT_PAGE)
                        .build(),
                    MediaItem.FLAG_BROWSABLE
                )
            )

            Single.just(trackList)
        }
    }

    private fun getRootMediaItems(): MutableList<MediaItem> =
        mutableListOf(
            MediaItem(
                MediaDescriptionCompat.Builder()
                    .setMediaId(MEDIA_REPOSTS_ID)
                    .setTitle("Reposts")
                    .build(),
                MediaItem.FLAG_BROWSABLE
            ),
            MediaItem(
                MediaDescriptionCompat.Builder()
                    .setMediaId(MEDIA_LIKES_ID)
                    .setTitle("Likes")
                    .build(),
                MediaItem.FLAG_BROWSABLE
            )
        )

    companion object {
        const val MEDIA_ROOT_ID = "root_id"
        const val MEDIA_REPOSTS_ID = "reposts_id"
        const val MEDIA_REPOSTS_NEXT_PAGE = "reposts_next_page"
        const val MEDIA_LIKES_ID = "likes_id"
    }
}