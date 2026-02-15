package com.vlad.simion.domain.usecase

import com.vlad.simion.domain.repository.GithubRepository
import javax.inject.Inject

class GetGithubRepositoryDetailsUseCase @Inject constructor(private val repository: GithubRepository) {
    suspend operator fun invoke(path: String) = repository.getRepositoryDetails(path)
}