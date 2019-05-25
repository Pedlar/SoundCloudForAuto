package org.notlocalhost.soundcloud.auto.models

data class RepostsCollection (
    val createdAt : String,
    val type : String,
    val track : SoundCloudTrack,
    val user : SoundCloudUser,
    val uuid : String
)