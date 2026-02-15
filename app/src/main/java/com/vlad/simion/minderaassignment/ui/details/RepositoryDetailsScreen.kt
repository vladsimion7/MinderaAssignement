package com.vlad.simion.minderaassignment.ui.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CallSplit
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vlad.simion.designsystem.MSpacing
import com.vlad.simion.designsystem.widgets.MInfoRow
import com.vlad.simion.designsystem.widgets.MText
import com.vlad.simion.domain.model.response.GithubRepositoryEntity
import com.vlad.simion.minderaassignment.R
import com.vlad.simion.minderaassignment.ui.PreviewsDataProvider

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun RepositoryDetailsScreen(
    repository: GithubRepositoryEntity,
    onBackClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    MText(
                        text = repository.name ?: stringResource(R.string.repository_details_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(MSpacing.space_md),
            verticalArrangement = Arrangement.spacedBy(MSpacing.space_md)
        ) {
            AuthorCard(
                repository = repository,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            )

            RepositoryInfoCard(
                repository = repository,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun AuthorCard(
    repository: GithubRepositoryEntity,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope
) {
    val owner = repository.owner

    with(sharedTransitionScope) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = MSpacing.space_2xs)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MSpacing.space_lg)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MSpacing.space_md)
                ) {

                    AsyncImage(
                        model = owner?.avatarUrl,
                        contentDescription = stringResource(R.string.author_avatar),
                        modifier = Modifier
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = "image-${repository.id}"),
                                animatedVisibilityScope = animatedContentScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 500)
                                },
                                renderInOverlayDuringTransition = false
                            )
                            .size(72.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        MText(
                            text = stringResource(R.string.author),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(MSpacing.space_2xs))
                        MText(
                            text = owner?.login ?: stringResource(R.string.unknown),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = "author-${repository.id}"),
                                    animatedVisibilityScope = animatedContentScope,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = 500)
                                    },
                                    renderInOverlayDuringTransition = false
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(MSpacing.space_md))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(MSpacing.space_md))

                MInfoRow(
                    icon = Icons.Default.Person,
                    label = stringResource(R.string.type),
                    value = owner?.type ?: stringResource(R.string.not_available)
                )

                Spacer(modifier = Modifier.height(MSpacing.space_sm))

                MInfoRow(
                    icon = Icons.Default.AccountCircle,
                    label = stringResource(R.string.user_id),
                    value = owner?.id?.toString() ?: stringResource(R.string.not_available)
                )

                Spacer(modifier = Modifier.height(MSpacing.space_sm))

                MInfoRow(
                    icon = Icons.Default.Link,
                    label = stringResource(R.string.profile_url),
                    value = owner?.htmlUrl ?: stringResource(R.string.not_available),
                    maxLines = 2
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun RepositoryInfoCard(
    repository: GithubRepositoryEntity,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope
) {
    with(sharedTransitionScope) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = MSpacing.space_2xs)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MSpacing.space_lg)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.FolderOpen,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(MSpacing.space_sm))
                    Column(modifier = Modifier.weight(1f)) {
                        MText(
                            text = stringResource(R.string.repository),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(MSpacing.space_2xs))
                        MText(
                            text = repository.name ?: stringResource(R.string.unknown),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(MSpacing.space_md))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(MSpacing.space_md))

                MInfoRow(
                    icon = Icons.Default.Label,
                    label = stringResource(R.string.full_name),
                    value = repository.fullName ?: stringResource(R.string.not_available)
                )

                Spacer(modifier = Modifier.height(MSpacing.space_sm))

                if (!repository.description.isNullOrBlank()) {
                    val description = repository.description!!
                    MInfoRow(
                        icon = Icons.Default.Description,
                        label = stringResource(R.string.description),
                        value = description,
                        maxLines = Int.MAX_VALUE
                    )
                    Spacer(modifier = Modifier.height(MSpacing.space_sm))
                }

                MInfoRow(
                    icon = Icons.Default.Star,
                    label = stringResource(R.string.stars),
                    value = repository.stargazersCount?.toString() ?: stringResource(R.string.zero),
                    iconTint = MaterialTheme.colorScheme.tertiary
                )

                Spacer(modifier = Modifier.height(MSpacing.space_sm))

                MInfoRow(
                    icon = Icons.Default.Link,
                    label = stringResource(R.string.url),
                    value = repository.htmlUrl ?: stringResource(R.string.not_available),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(MSpacing.space_sm))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(MSpacing.space_xs)
                ) {
                    if (repository.private == true) {
                        AssistChip(
                            onClick = { },
                            label = { MText(stringResource(R.string.private_repo)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        )
                    } else {
                        AssistChip(
                            onClick = { },
                            label = { MText(stringResource(R.string.public_repo)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.LockOpen,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        )
                    }

                    if (repository.fork == true) {
                        AssistChip(
                            onClick = { },
                            label = { MText(stringResource(R.string.fork)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.CallSplit,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RepositoryDetailsScreenPreview() {
    MaterialTheme {
        SharedTransitionLayout {
            AnimatedContent(
                targetState = true,
                label = "preview"
            ) { _ ->
                RepositoryDetailsScreen(
                    repository = PreviewsDataProvider.mockRepositoryDetails,
                    onBackClick = {},
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@AnimatedContent
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun AuthorCardPreview() {
    MaterialTheme {
        SharedTransitionLayout {
            AnimatedContent(
                targetState = true,
                label = "preview"
            ) { _ ->
                AuthorCard(
                    repository = PreviewsDataProvider.mockRepositoryDetails,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@AnimatedContent
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun RepositoryInfoCardPreview() {
    MaterialTheme {
        SharedTransitionLayout {
            AnimatedContent(
                targetState = true,
                label = "preview"
            ) { _ ->
                RepositoryInfoCard(
                    repository = PreviewsDataProvider.mockRepositoryDetails,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@AnimatedContent
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MInfoRowPreview() {
    MaterialTheme {
        MInfoRow(
            icon = Icons.Default.Star,
            label = "Stars",
            value = "1234"
        )
    }
}
