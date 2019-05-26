package org.notlocalhost.soundcloud.auto.models.track

data class VisualsBase (
    val urn : String,
    val enabled : Boolean,
    val visuals : List<Visuals>
)