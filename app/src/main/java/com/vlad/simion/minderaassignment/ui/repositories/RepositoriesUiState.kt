package com.vlad.simion.minderaassignment.ui.repositories

import com.vlad.simion.domain.model.response.GithubRepositoryEntity

sealed interface RepositoriesUiState {
    data object Loading : RepositoriesUiState
    data class Success(val repositories: List<GithubRepositoryEntity>) : RepositoriesUiState
    data class Error(val message: String) : RepositoriesUiState
}

sealed interface RepositoriesUiEvent {
    data object OnRefresh : RepositoriesUiEvent
    data object OnSwitchToMockData : RepositoriesUiEvent
}