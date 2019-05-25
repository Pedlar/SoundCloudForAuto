package org.notlocalhost.soundcloud.auto.models.track

import org.notlocalhost.soundcloud.auto.models.track.Transcodings

data class Media (
    val transcodings : List<Transcodings>
)