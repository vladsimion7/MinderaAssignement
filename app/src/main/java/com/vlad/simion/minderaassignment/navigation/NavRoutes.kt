package com.vlad.simion.minderaassignment.navigation

sealed class NavRoutes(val route: String) {
    data object Repositories : NavRoutes("repositories")
    data object RepositoryDetails : NavRoutes("repository_details/{repositoryId}") {
        fun createRoute(repositoryId: Int) = "repository_details/$repositoryId"
    }
}
