package com.turtlemint.turtlemintproject.view.data.remote

import com.turtlemint.turtlemintproject.view.data.model.Issue
import retrofit2.Response
import retrofit2.http.GET
// for accesing api calls
interface ApiInterface {

//    @GET("v2/top-headlines")
//    suspend fun getBreakingNews(
//        @Query("country") countryCode: String = "tr",
//        @Query("page") pageNumber: Int = 1,
//        @Query("apiKey") apiKey: String = API_KEY
//    ): Response<IssuesResponse>
//
//    @GET("v2/everything")
//    suspend fun searchForNews(
//        @Query("q") searchQuery: String,
//        @Query("page") pageNumber: Int = 1,
//        @Query("apiKey") apiKey: String = API_KEY
//    ): Response<IssuesResponse>

    @GET("issues")
    suspend fun getIssuesList(): Response<List<Issue>>

}