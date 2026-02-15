package com.vlad.simion.minderaassignment.ui.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.simion.domain.common.DataSourceType
import com.vlad.simion.domain.common.Result
import com.vlad.simion.domain.model.response.GithubRepositoryEntity
import com.vlad.simion.domain.usecase.GetGithubRepositoriesUseCase
import com.vlad.simion.domain.usecase.GetGithubRepositoryDetailsUseCase
import com.vlad.simion.domain.usecase.SetDataSourceTypeUseCase
import com.vlad.simion.minderaassignment.di.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(
    private val getRepositories: GetGithubRepositoriesUseCase,
    private val getRepositoryDetails: GetGithubRepositoryDetailsUseCase,
    private val setDataSourceType: SetDataSourceTypeUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<RepositoriesUiState>(RepositoriesUiState.Loading)
    val uiState: StateFlow<RepositoriesUiState> = _uiState.asStateFlow()

    private val _restartEffect = MutableSharedFlow<Unit>()
    val restartEffect: SharedFlow<Unit> = _restartEffect.asSharedFlow()

    init {
        loadRepositories()
    }

    fun handleEvents(event: RepositoriesUiEvent) {
        when (event) {
            RepositoriesUiEvent.OnRefresh -> loadRepositories()
            RepositoriesUiEvent.OnSwitchToMockData -> switchToMockData()
        }
    }

    private fun switchToMockData() {
        viewModelScope.launch(dispatcherProvider.io) {
            setDataSourceType(DataSourceType.MOCK)
            _restartEffect.emit(Unit)
        }
    }

    private fun loadRepositories() {
        viewModelScope.launch(dispatcherProvider.io) {
            _uiState.value = RepositoriesUiState.Loading
            val result = getRepositories()

            when (result) {
                is Result.Success -> {
                    _uiState.value = RepositoriesUiState.Success(result.data)
                    fetchRepositoryDetails(result.data)
                }

                is Result.Error -> {
                    _uiState.value = RepositoriesUiState.Error(result.message)
                }
            }
        }
    }

    private fun fetchRepositoryDetails(repositories: List<GithubRepositoryEntity>) {
        viewModelScope.launch(dispatcherProvider.io) {
            val deferredResults = repositories.mapNotNull { repository ->
                repository.fullName?.let { fullName ->
                    async {
                        when (val detailsResult = getRepositoryDetails(fullName)) {
                            is Result.Success -> {
                                fullName to detailsResult.data.stargazersCount
                            }

                            is Result.Error -> {
                                null
                            }
                        }
                    }
                }
            }

            val results = deferredResults.awaitAll().filterNotNull().toMap()

            if (results.isNotEmpty()) {
                val updatedRepositories =
                    repositories.map { repo ->
                        repo.fullName?.let { fullName ->
                            results[fullName]?.let { starCount ->
                                repo.copy(stargazersCount = starCount)
                            } ?: repo
                        } ?: repo
                    }

                _uiState.update { RepositoriesUiState.Success(updatedRepositories) }
            }
        }
    }
}
