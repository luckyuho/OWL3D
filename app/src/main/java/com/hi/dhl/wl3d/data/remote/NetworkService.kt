package com.hi.dhl.wl3d.data.remote

import com.hi.dhl.wl3d.data.entity.NetWorkDataInfo
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
interface NetworkService {

    @GET("/dev/media")
    suspend fun fetchDataList(@Query("startToken") nextToken: String): NetWorkDataInfo

}