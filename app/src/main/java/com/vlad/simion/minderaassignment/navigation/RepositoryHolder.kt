package com.vlad.simion.minderaassignment.navigation

import com.vlad.simion.domain.model.response.GithubRepositoryEntity

object RepositoryHolder {
    private var selectedRepository: GithubRepositoryEntity? = null

    fun setRepository(repository: GithubRepositoryEntity) {
        selectedRepository = repository
    }

    fun getRepository(): GithubRepositoryEntity? {
        return selectedRepository
    }

    fun clearRepository() {
        selectedRepository = null
    }
}
