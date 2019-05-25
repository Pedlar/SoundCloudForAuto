package org.notlocalhost.soundcloud.auto.models.track

data class PublisherMetaData (
    val urn : String,
    val containsMusic : Boolean,
    val artist : String,
    val writerComposer : String,
    val isrc : String,
    val id : Int
)