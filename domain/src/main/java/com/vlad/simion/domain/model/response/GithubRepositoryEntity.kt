package com.vlad.simion.domain.model.response

data class GithubRepositoryEntity(
    val id: Int?,
    val name: String?,
    val fullName: String?,
    val description: String?,
    val stargazersCount: Int?,
    val htmlUrl: String?,
    val private: Boolean?,
    val fork: Boolean?,
    val owner: OwnerEntity?
)

data class OwnerEntity(
    val login: String?,
    val id: Int?,
    val avatarUrl: String?,
    val htmlUrl: String?,
    val type: String?
)