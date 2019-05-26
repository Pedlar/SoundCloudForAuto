package org.notlocalhost.soundcloud.auto.models

import android.net.Uri
import android.support.v4.media.MediaDescriptionCompat
import org.notlocalhost.soundcloud.auto.models.track.Media
import org.notlocalhost.soundcloud.auto.models.track.PublisherMetaData
import org.notlocalhost.soundcloud.auto.models.track.VisualsBase

data class SoundCloudTrack (
    val commentCount : Int,
    val fullDuration : Int,
    val downloadable : Boolean,
    val createdAt : String,
    val description : String,
    val media : Media,
    val title : String,
    val publisherMetaData : PublisherMetaData,
    val duration : Int,
    val hasDownloadsLeft : Boolean,
    val artworkUrl : String,
    val public : Boolean,
    val streamable : Boolean,
    val tagList : String,
    val downloadUrl : String,
    val genre : String,
    val id : Int,
    val repostsCount : Int,
    val state : String,
    val labelName : String,
    val lastModified : String,
    val commentable : Boolean,
    val policy : String,
    val visuals : VisualsBase,
    val kind : String,
    val purchaseUrl : String,
    val sharing : String,
    val uri : String,
    val secretToken : String,
    val downloadCount : Int,
    val likesCount : Int,
    val urn : String,
    val license : String,
    val purchaseTitle : String,
    val displayDate : String,
    val embeddableBy : String,
    val releaseDate : String,
    val userId : Int,
    val monetizationModel : String,
    val waveformUrl : String,
    val permalink : String,
    val permalinkUrl : String,
    val user : SoundCloudUser,
    val playbackCount : Int
) {

    fun toMediaDescription() = MediaDescriptionCompat.Builder()
        .setMediaId(id.toString())
        .setMediaUri(Uri.parse(uri))
        .setTitle(title)
        .setIconUri(Uri.parse(artworkUrl))
        .build()
}