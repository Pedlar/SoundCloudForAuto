package org.notlocalhost.soundcloud.auto.api.v2

import io.reactivex.Observable
import org.notlocalhost.soundcloud.auto.models.RepostsWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface v2Service {
    @GET("/stream/users/{user}/reposts")
    fun getReposts(
        @Path("user") user: Int,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: String = "0",
        @Query("linked_partitioning") linkedPartitioning: String = "1n"): Observable<RepostsWrapper>
}