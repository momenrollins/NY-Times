package com.momen.nytimes.api

import com.momen.nytimes.data.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTimesApi {

    @GET("svc/mostpopular/v2/mostviewed/{section}/{period}.json")
    suspend fun getNews(
        @Path("section") section: String,
        @Path("period") period: Int,
        @Query("api-key") api_key: String
    ): Response<NewsResponse>
}