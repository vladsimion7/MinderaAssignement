package com.vlad.simion.domain.repository

import com.vlad.simion.domain.common.Result
import com.vlad.simion.domain.model.response.GithubRepositoryDetailsEntity
import com.vlad.simion.domain.model.response.GithubRepositoryEntity

interface GithubRepository {
    suspend fun getPublicRepositories(): Result<List<GithubRepositoryEntity>>
    suspend fun getRepositoryDetails(path: String): Result<GithubRepositoryDetailsEntity>
}