package com.vlad.simion.minderaassignment.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.vlad.simion.minderaassignment.ui.details.RepositoryDetailsScreen
import com.vlad.simion.minderaassignment.ui.repositories.RepositoriesListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Repositories.route
        ) {
            composable(route = NavRoutes.Repositories.route) {
                RepositoriesListScreen(
                    onRepositoryClick = { repository ->
                        RepositoryHolder.setRepository(repository)
                        repository.id?.let { id ->
                            navController.navigate(
                                route = NavRoutes.RepositoryDetails.createRoute(id),
                                navOptions = navOptions {
                                    launchSingleTop = true
                                }
                            )
                        }
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this
                )
            }

            composable(
                route = NavRoutes.RepositoryDetails.route,
                arguments = listOf(
                    navArgument("repositoryId") { type = NavType.IntType }
                )
            ) {
                val repository = RepositoryHolder.getRepository()

                repository?.let {
                    RepositoryDetailsScreen(
                        repository = it,
                        onBackClick = {
                            RepositoryHolder.clearRepository()
                            navController.popBackStack()
                        },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this
                    )
                }
            }
        }
    }
}
