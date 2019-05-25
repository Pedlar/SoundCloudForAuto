package org.notlocalhost.soundcloud.auto.models

import com.google.gson.annotations.SerializedName
import org.notlocalhost.kotlin.extenstions.DynamicInitializer

data class SoundCloudUser constructor(
    val id: Int,
    val permalink: String,
    val username: String,
    val uri: String,
    val permalinkUrl: String,
    val avatarUrl: String,
    val country: String,
    val fullName: String,
    val city: String,
    val description: String,
    @SerializedName("discogs-name")
    val discogsName: String,
    @SerializedName("myspace-name")
    val myspacName: String,
    val website: String,
    @SerializedName("website-title")
    val websiteTitle: String,
    val online: Boolean,
    val trackCount: Int,
    val playlistCount: Int,
    val followersCount: Int,
    val followingsCount: Int,
    val publicFavoritesCount: Int
) {
    companion object: DynamicInitializer<SoundCloudUser>
}