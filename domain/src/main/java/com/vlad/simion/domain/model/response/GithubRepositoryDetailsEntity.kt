package com.vlad.simion.domain.model.response

data class GithubRepositoryDetailsEntity(
    val id: Long?,
    val nodeId: String?,
    val name: String?,
    val fullName: String?,
    val private: Boolean?,
    val owner: OwnerEntity?,
    val htmlUrl: String?,
    val description: String?,
    val fork: Boolean?,
    val createdAt: String?,
    val updatedAt: String?,
    val pushedAt: String?,
    val homepage: String?,
    val size: Int?,
    val stargazersCount: Int?,
    val watchersCount: Int?,
    val language: String?,
    val forksCount: Int?,
    val openIssuesCount: Int?,
    val license: LicenseEntity?,
    val topics: List<String>?,
    val visibility: String?,
    val defaultBranch: String?,
    val networkCount: Int?,
    val subscribersCount: Int?
)

data class LicenseEntity(
    val key: String?,
    val name: String?,
    val spdxId: String?,
    val url: String?
)
