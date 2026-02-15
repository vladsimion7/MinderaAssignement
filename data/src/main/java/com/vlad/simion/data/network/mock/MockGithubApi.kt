package com.vlad.simion.data.network.mock

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vlad.simion.data.model.response.GithubRepositoryDetailsDto
import com.vlad.simion.data.model.response.GithubRepositoryDto
import com.vlad.simion.data.network.GithubApi
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class MockGithubApi(private val context: Context) : GithubApi {
    override suspend fun getRepositories(): Response<List<GithubRepositoryDto>> {
        return try {
            val json = context.assets.open("github_repositories_mock.json")
                .bufferedReader()
                .use { it.readText() }

            val listType = object : TypeToken<List<GithubRepositoryDto>>() {}.type
            val dtoList: List<GithubRepositoryDto> = Gson().fromJson(json, listType)

            Response.success(dtoList)
        } catch (e: Exception) {
            Log.e("MockGithubApi", "Failed to load repositories", e)
            Response.error(500, "Failed to load repositories".toResponseBody(null))
        }
    }

    override suspend fun getRepositoryDetails(path: String): Response<GithubRepositoryDetailsDto> {
        return try {
            val json = context.assets.open("github_repository_details_mock.json")
                .bufferedReader()
                .use { it.readText() }

            val dto = Gson().fromJson(json, GithubRepositoryDetailsDto::class.java)

            Response.success(dto)
        } catch (e: Exception) {
            Log.e("MockGithubApi", "Failed to load repository details", e)
            Response.error(500, "Failed to load repository details".toResponseBody(null))
        }
    }
}