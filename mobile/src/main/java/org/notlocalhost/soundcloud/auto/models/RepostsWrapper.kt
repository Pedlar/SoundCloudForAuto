package org.notlocalhost.soundcloud.auto.models

data class RepostsWrapper (
    val collection : List<RepostsCollection>,
    val nextHref : String,
    val queryUrn : String
)