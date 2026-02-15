package com.vlad.simion.data.repository

import com.vlad.simion.data.model.response.toDomainModel
import com.vlad.simion.data.network.GithubApi
import com.vlad.simion.data.util.executeSafely
import com.vlad.simion.domain.common.Result
import com.vlad.simion.domain.model.response.GithubRepositoryDetailsEntity
import com.vlad.simion.domain.model.response.GithubRepositoryEntity
import com.vlad.simion.domain.repository.GithubRepository

class GithubRepositoryImpl(
    private val api: GithubApi
) : GithubRepository {
    override suspend fun getPublicRepositories(): Result<List<GithubRepositoryEntity>> {
        return executeSafely(
            apiCall = { api.getRepositories() },
            transform = { response ->
                response.map { it.toDomainModel() }
            }
        )
    }

    override suspend fun getRepositoryDetails(path: String): Result<GithubRepositoryDetailsEntity> {
        return executeSafely(
            apiCall = { api.getRepositoryDetails(path) },
            transform = { response ->
                response.toDomainModel()
            }
        )
    }
}