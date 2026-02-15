package com.vlad.simion.data.network

import com.vlad.simion.data.model.response.GithubRepositoryDetailsDto
import com.vlad.simion.data.model.response.GithubRepositoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/repositories")
    suspend fun getRepositories(): Response<List<GithubRepositoryDto>>

    @GET("/{path}")
    suspend fun getRepositoryDetails(
        @Path(
            value = "path",
            encoded = true
        ) path: String
    ): Response<GithubRepositoryDetailsDto>
}