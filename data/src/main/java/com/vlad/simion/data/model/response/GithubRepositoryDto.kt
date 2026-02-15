package com.vlad.simion.data.model.response

import com.google.gson.annotations.SerializedName
import com.vlad.simion.domain.model.response.GithubRepositoryEntity
import com.vlad.simion.domain.model.response.OwnerEntity

data class GithubRepositoryDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("private")
    val private: Boolean?,
    @SerializedName("owner")
    val owner: OwnerDto?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
    @SerializedName("fork")
    val fork: Boolean?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("forks_url")
    val forksUrl: String?,
    @SerializedName("keys_url")
    val keysUrl: String?,
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String?,
    @SerializedName("teams_url")
    val teamsUrl: String?,
    @SerializedName("hooks_url")
    val hooksUrl: String?,
    @SerializedName("issue_events_url")
    val issueEventsUrl: String?,
    @SerializedName("events_url")
    val eventsUrl: String?,
    @SerializedName("assignees_url")
    val assigneesUrl: String?,
    @SerializedName("branches_url")
    val branchesUrl: String?,
    @SerializedName("tags_url")
    val tagsUrl: String?,
    @SerializedName("blobs_url")
    val blobsUrl: String?,
    @SerializedName("git_tags_url")
    val gitTagsUrl: String?,
    @SerializedName("git_refs_url")
    val gitRefsUrl: String?,
    @SerializedName("trees_url")
    val treesUrl: String?,
    @SerializedName("statuses_url")
    val statusesUrl: String?,
    @SerializedName("languages_url")
    val languagesUrl: String?,
    @SerializedName("stargazers_url")
    val stargazersUrl: String?,
    @SerializedName("contributors_url")
    val contributorsUrl: String?,
    @SerializedName("subscribers_url")
    val subscribersUrl: String?,
    @SerializedName("subscription_url")
    val subscriptionUrl: String?,
    @SerializedName("commits_url")
    val commitsUrl: String?,
    @SerializedName("git_commits_url")
    val gitCommitsUrl: String?,
    @SerializedName("comments_url")
    val commentsUrl: String?,
    @SerializedName("issue_comment_url")
    val issueCommentUrl: String?,
    @SerializedName("contents_url")
    val contentsUrl: String?,
    @SerializedName("compare_url")
    val compareUrl: String?,
    @SerializedName("merges_url")
    val mergesUrl: String?,
    @SerializedName("archive_url")
    val archiveUrl: String?,
    @SerializedName("downloads_url")
    val downloadsUrl: String?,
    @SerializedName("issues_url")
    val issuesUrl: String?,
    @SerializedName("pulls_url")
    val pullsUrl: String?,
    @SerializedName("milestones_url")
    val milestonesUrl: String?,
    @SerializedName("notifications_url")
    val notificationsUrl: String?,
    @SerializedName("labels_url")
    val labelsUrl: String?,
    @SerializedName("releases_url")
    val releasesUrl: String?,
    @SerializedName("deployments_url")
    val deploymentsUrl: String?
)

data class OwnerDto(
    @SerializedName("login")
    val login: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("gravatar_id")
    val gravatarId: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("followers_url")
    val followersUrl: String?,
    @SerializedName("following_url")
    val followingUrl: String?,
    @SerializedName("gists_url")
    val gistsUrl: String?,
    @SerializedName("starred_url")
    val starredUrl: String?,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String?,
    @SerializedName("organizations_url")
    val organizationsUrl: String?,
    @SerializedName("repos_url")
    val reposUrl: String?,
    @SerializedName("events_url")
    val eventsUrl: String?,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("user_view_type")
    val userViewType: String?,
    @SerializedName("site_admin")
    val siteAdmin: Boolean?
)

fun GithubRepositoryDto.toDomainModel() = GithubRepositoryEntity(
    id = this.id,
    name = this.name,
    fullName = this.fullName,
    description = this.description,
    stargazersCount = this.stargazersCount,
    htmlUrl = this.htmlUrl,
    private = this.private,
    fork = this.fork,
    owner = this.owner?.toDomainModel()
)

fun OwnerDto.toDomainModel() = OwnerEntity(
    login = this.login,
    id = this.id,
    avatarUrl = this.avatarUrl,
    htmlUrl = this.htmlUrl,
    type = this.type
)