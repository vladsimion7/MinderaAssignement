package com.vlad.simion.minderaassignment.ui.repositories

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.simion.designsystem.widgets.MButton
import com.vlad.simion.designsystem.widgets.MListItem
import com.vlad.simion.designsystem.widgets.MText
import com.vlad.simion.domain.model.response.GithubRepositoryEntity
import com.vlad.simion.domain.model.response.OwnerEntity
import com.vlad.simion.minderaassignment.R
import com.vlad.simion.minderaassignment.ui.PreviewsDataProvider
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoriesListScreen(
    onRepositoryClick: (GithubRepositoryEntity) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    viewModel: RepositoriesListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val conMText = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.restartEffect.collect {
            val intent = conMText.packageManager.getLaunchIntentForPackage(conMText.packageName)
            intent?.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent?.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            conMText.startActivity(intent)
            exitProcess(0)
        }
    }

    RepositoriesListScreenContent(
        uiState = uiState,
        onRefresh = { viewModel.handleEvents(RepositoriesUiEvent.OnRefresh) },
        onRepositoryClick = onRepositoryClick,
        onRetry = { viewModel.handleEvents(RepositoriesUiEvent.OnRefresh) },
        onSwitchToMockData = { viewModel.handleEvents(RepositoriesUiEvent.OnSwitchToMockData) },
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RepositoriesListScreenPreview() {
    MaterialTheme {
        RepositoriesListScreenContent(
            uiState = RepositoriesUiState.Success(
                repositories = PreviewsDataProvider.mockRepositories
            ),
            onRefresh = {},
            onRepositoryClick = {},
            onRetry = {},
            onSwitchToMockData = {},
            sharedTransitionScope = null,
            animatedContentScope = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RepositoriesListScreenContent(
    uiState: RepositoriesUiState,
    onRefresh: () -> Unit,
    onRepositoryClick: (GithubRepositoryEntity) -> Unit,
    onRetry: () -> Unit,
    onSwitchToMockData: () -> Unit,
    sharedTransitionScope: SharedTransitionScope?,
    animatedContentScope: AnimatedVisibilityScope?
) {
    val isRefreshing = uiState is RepositoriesUiState.Loading

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    MText(
                        text = stringResource(R.string.repositories_title),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE8E8F0))
        ) {
            when (val state = uiState) {
                is RepositoriesUiState.Loading -> {
                }

                is RepositoriesUiState.Success -> {
                    if (state.repositories.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            MText(text = stringResource(R.string.no_repositories_found))
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                horizontal = 16.dp, vertical = 16.dp
                            ),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(
                                items = state.repositories,
                                key = { repository -> (repository.id.toString() + repository.name) },
                                contentType = { "repository" }) { repository ->
                                if (sharedTransitionScope != null && animatedContentScope != null) {
                                    MListItem(
                                        imageUrl = repository.owner?.avatarUrl,
                                        title = repository.name ?: stringResource(R.string.unknown),
                                        author = repository.owner?.login
                                            ?: stringResource(R.string.unknown),
                                        stars = repository.stargazersCount,
                                        repositoryUrl = repository.htmlUrl,
                                        onClick = {
                                            onRepositoryClick(repository)
                                        },
                                        imageContentDescription = stringResource(
                                            R.string.avatar_of, repository.owner?.login.orEmpty()
                                        ),
                                        sharedId = repository.id ?: 0,
                                        sharedTransitionScope = sharedTransitionScope,
                                        animatedContentScope = animatedContentScope
                                    )
                                } else {
                                    RepositoryItemPreview(repository = repository)
                                }
                            }
                        }
                    }
                }

                is RepositoriesUiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            MText(
                                text = stringResource(R.string.error_message, state.message),
                                color = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            MButton(onClick = onRetry) {
                                MText(stringResource(R.string.retry))
                            }
                            MButton(onClick = onSwitchToMockData) {
                                MText(stringResource(R.string.switch_to_mock_data))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RepositoryItemPreview(repository: GithubRepositoryEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Gray, CircleShape)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        MText(
                            text = repository.name ?: "Unknown",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        MText(
                            text = "by ${repository.owner?.login ?: "Unknown"}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    MText(
                        text = "⭐", style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    MText(
                        text = repository.stargazersCount.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            MText(
                text = repository.htmlUrl?.replace("https://", "")?.replace("http://", "") ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFE8E8E8), shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
            )
        }
    }
}
