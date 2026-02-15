package com.vlad.simion.domain

import com.vlad.simion.domain.model.response.GithubRepositoryDetailsEntity
import com.vlad.simion.domain.model.response.GithubRepositoryEntity
import com.vlad.simion.domain.model.response.LicenseEntity
import com.vlad.simion.domain.model.response.OwnerEntity

/**
 * Centralized mock data for unit tests.
 * Contains pre-created test data instances that can be used directly in tests.
 */
object TestMockData {

    object ErrorMessages {
        const val NETWORK_ERROR = "Network error"
        const val UNKNOWN_ERROR = "Unknown error"
        const val REPOSITORY_NOT_FOUND = "Repository not found"
        const val NETWORK_TIMEOUT = "Network timeout"
    }

    object ErrorCodes {
        const val INTERNAL_SERVER_ERROR = 500
        const val NOT_FOUND = 404
        const val REQUEST_TIMEOUT = 408
    }

    object Paths {
        const val DEFAULT_PATH = "testuser/testrepo"
        const val PATH_1 = "user1/repo1"
        const val PATH_2 = "user2/repo2"
    }

    val defaultOwner = OwnerEntity(
        login = "testuser",
        id = 1,
        avatarUrl = "https://avatar.url",
        htmlUrl = "https://github.com/testuser",
        type = "User"
    )

    val mitLicense = LicenseEntity(
        key = "mit",
        name = "MIT License",
        spdxId = "MIT",
        url = "https://opensource.org/licenses/MIT"
    )

    val repository1 = GithubRepositoryEntity(
        id = 1,
        name = "repo1",
        fullName = "testuser/repo1",
        description = "Test repository 1",
        stargazersCount = 100,
        htmlUrl = "https://github.com/testuser/repo1",
        private = false,
        fork = false,
        owner = defaultOwner
    )

    val repository2 = GithubRepositoryEntity(
        id = 2,
        name = "repo2",
        fullName = "testuser/repo2",
        description = "Test repository 2",
        stargazersCount = 200,
        htmlUrl = "https://github.com/testuser/repo2",
        private = false,
        fork = false,
        owner = defaultOwner
    )

    val repositoryList = listOf(repository1, repository2)

    val emptyRepositoryList = emptyList<GithubRepositoryEntity>()

    val fullRepositoryDetails = GithubRepositoryDetailsEntity(
        id = 1L,
        nodeId = "MDEwOlJlcG9zaXRvcnkx",
        name = "testrepo",
        fullName = Paths.DEFAULT_PATH,
        private = false,
        owner = defaultOwner,
        htmlUrl = "https://github.com/${Paths.DEFAULT_PATH}",
        description = "Test repository description",
        fork = false,
        createdAt = "2023-01-01T00:00:00Z",
        updatedAt = "2024-01-01T00:00:00Z",
        pushedAt = "2024-02-01T00:00:00Z",
        homepage = "https://testrepo.com",
        size = 1024,
        stargazersCount = 500,
        watchersCount = 450,
        language = "Kotlin",
        forksCount = 50,
        openIssuesCount = 10,
        license = mitLicense,
        topics = listOf("android", "kotlin", "testing"),
        visibility = "public",
        defaultBranch = "main",
        networkCount = 60,
        subscribersCount = 100
    )

    val minimalRepositoryDetails = GithubRepositoryDetailsEntity(
        id = 1L,
        nodeId = null,
        name = "testrepo",
        fullName = Paths.DEFAULT_PATH,
        private = false,
        owner = null,
        htmlUrl = "https://github.com/${Paths.DEFAULT_PATH}",
        description = null,
        fork = false,
        createdAt = null,
        updatedAt = null,
        pushedAt = null,
        homepage = null,
        size = null,
        stargazersCount = null,
        watchersCount = null,
        language = null,
        forksCount = null,
        openIssuesCount = null,
        license = null,
        topics = null,
        visibility = null,
        defaultBranch = null,
        networkCount = null,
        subscribersCount = null
    )

    val repositoryDetails1 = GithubRepositoryDetailsEntity(
        id = 1L,
        nodeId = null,
        name = "repo1",
        fullName = Paths.PATH_1,
        private = false,
        owner = null,
        htmlUrl = "https://github.com/${Paths.PATH_1}",
        description = null,
        fork = false,
        createdAt = null,
        updatedAt = null,
        pushedAt = null,
        homepage = null,
        size = null,
        stargazersCount = null,
        watchersCount = null,
        language = null,
        forksCount = null,
        openIssuesCount = null,
        license = null,
        topics = null,
        visibility = null,
        defaultBranch = null,
        networkCount = null,
        subscribersCount = null
    )

    val repositoryDetails2 = GithubRepositoryDetailsEntity(
        id = 2L,
        nodeId = null,
        name = "repo2",
        fullName = Paths.PATH_2,
        private = false,
        owner = null,
        htmlUrl = "https://github.com/${Paths.PATH_2}",
        description = null,
        fork = false,
        createdAt = null,
        updatedAt = null,
        pushedAt = null,
        homepage = null,
        size = null,
        stargazersCount = null,
        watchersCount = null,
        language = null,
        forksCount = null,
        openIssuesCount = null,
        license = null,
        topics = null,
        visibility = null,
        defaultBranch = null,
        networkCount = null,
        subscribersCount = null
    )
}
