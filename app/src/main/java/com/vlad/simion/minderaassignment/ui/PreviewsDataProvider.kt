package com.vlad.simion.minderaassignment.ui

import com.vlad.simion.domain.model.response.GithubRepositoryEntity
import com.vlad.simion.domain.model.response.OwnerEntity

object PreviewsDataProvider {
    private val mockOwnerKotlinBy = OwnerEntity(
        login = "KotlinBy",
        id = 123,
        avatarUrl = "https://avatars.githubusercontent.com/u/1234567",
        htmlUrl = "https://github.com/KotlinBy",
        type = "Organization"
    )

    private val mockOwnerAndroid = OwnerEntity(
        login = "android",
        id = 456,
        avatarUrl = "https://avatars.githubusercontent.com/u/7654321",
        htmlUrl = "https://github.com/android",
        type = "Organization"
    )

    private val mockOwnerKotlin = OwnerEntity(
        login = "Kotlin",
        id = 789,
        avatarUrl = "https://avatars.githubusercontent.com/u/1234",
        htmlUrl = "https://github.com/Kotlin",
        type = "Organization"
    )

    private val mockOwnerSquare = OwnerEntity(
        login = "square",
        id = 321,
        avatarUrl = "https://avatars.githubusercontent.com/u/98765",
        htmlUrl = "https://github.com/square",
        type = "Organization"
    )

    private val mockOwnerJohnDoe = OwnerEntity(
        id = 789,
        login = "johndoe",
        avatarUrl = "https://avatars.githubusercontent.com/u/789?v=4",
        htmlUrl = "https://github.com/johndoe",
        type = "User"
    )

    private val mockRepositoryAwesomeKotlin = GithubRepositoryEntity(
        id = 1,
        name = "awesome-kotlin",
        fullName = "KotlinBy/awesome-kotlin",
        description = "A curated list of awesome Kotlin resources",
        stargazersCount = 10500,
        htmlUrl = "https://github.com/KotlinBy/awesome-kotlin",
        private = false,
        fork = false,
        owner = mockOwnerKotlinBy
    )

    private val mockRepositoryComposeSamples = GithubRepositoryEntity(
        id = 2,
        name = "compose-samples",
        fullName = "android/compose-samples",
        description = "Official Jetpack Compose samples",
        stargazersCount = 8500,
        htmlUrl = "https://github.com/android/compose-samples",
        private = false,
        fork = false,
        owner = mockOwnerAndroid
    )

    private val mockRepositoryKotlinCoroutines = GithubRepositoryEntity(
        id = 3,
        name = "kotlin-coroutines",
        fullName = "kotlin/kotlinx.coroutines",
        description = "Library support for Kotlin coroutines",
        stargazersCount = 12000,
        htmlUrl = "https://github.com/Kotlin/kotlinx.coroutines",
        private = false,
        fork = false,
        owner = mockOwnerKotlin
    )

    private val mockRepositoryRetrofit = GithubRepositoryEntity(
        id = 4,
        name = "retrofit",
        fullName = "square/retrofit",
        description = "A type-safe HTTP client for Android and Java",
        stargazersCount = 42000,
        htmlUrl = "https://github.com/square/retrofit",
        private = false,
        fork = false,
        owner = mockOwnerSquare
    )

    val mockRepositoryDetails = GithubRepositoryEntity(
        id = 123456,
        name = "awesome-kotlin-project",
        fullName = "johndoe/awesome-kotlin-project",
        description = "An amazing Kotlin project showcasing modern Android development with Jetpack Compose, Clean Architecture, and best practices.",
        htmlUrl = "https://github.com/johndoe/awesome-kotlin-project",
        stargazersCount = 1234,
        private = false,
        fork = false,
        owner = mockOwnerJohnDoe
    )

    val mockRepositories = listOf(
        mockRepositoryAwesomeKotlin,
        mockRepositoryComposeSamples,
        mockRepositoryKotlinCoroutines,
        mockRepositoryRetrofit
    )
}