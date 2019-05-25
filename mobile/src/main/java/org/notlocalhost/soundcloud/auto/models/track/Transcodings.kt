package org.notlocalhost.soundcloud.auto.models.track

data class Transcodings (
    val url : String,
    val preset : String,
    val duration : Int,
    val snipped : Boolean,
    val format : Format,
    val quality : String
)