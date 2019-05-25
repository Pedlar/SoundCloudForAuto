package org.notlocalhost.soundcloud.auto.services

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserCompat.MediaItem
import androidx.media.MediaBrowserServiceCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.notlocalhost.soundcloud.auto.api.ScApi
import org.notlocalhost.soundcloud.auto.services.MediaDisplayHandler.Companion.MEDIA_ROOT_ID

import java.util.ArrayList
import javax.inject.Inject

class MyMusicService : MediaBrowserServiceCompat() {
    private lateinit var mSession: MediaSessionCompat

    @Inject
    lateinit var mediaDisplayHandler: MediaDisplayHandler

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this)

        mSession = MediaSessionCompat(this, "MyMusicService")
        sessionToken = mSession.sessionToken
        mSession.setCallback(MediaSessionCallback())
    }

    override fun onDestroy() {
        mSession.release()
    }

    override fun onGetRoot( clientPackageName: String, clientUid: Int, rootHints: Bundle?)
            = BrowserRoot(MEDIA_ROOT_ID, null)

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaItem>>) { }

    @SuppressLint("CheckResult")
    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaItem>>, options: Bundle) {
        result.detach()
        // TODO:: Dispose
        mediaDisplayHandler
            .handleLoadChildren(parentId, options)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ mediaItems ->
                result.sendResult(mediaItems)
            }, {
                Log.e("MusicService", "Error Loading Children", it)
            })
    }

    private inner class MediaSessionCallback : MediaSessionCompat.Callback() {
        override fun onPlay() {}

        override fun onSkipToQueueItem(queueId: Long) {}

        override fun onSeekTo(position: Long) {}

        override fun onPlayFromMediaId(mediaId: String?, extras: Bundle?) {}

        override fun onPause() {}

        override fun onStop() {}

        override fun onSkipToNext() {}

        override fun onSkipToPrevious() {}

        override fun onCustomAction(action: String?, extras: Bundle?) {}

        override fun onPlayFromSearch(query: String?, extras: Bundle?) {}
    }

}
