package com.vlad.simion.domain.usecase

import com.vlad.simion.domain.TestMockData
import com.vlad.simion.domain.common.Result
import com.vlad.simion.domain.model.response.GithubRepositoryEntity
import com.vlad.simion.domain.repository.GithubRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetGithubRepositoriesUseCaseTest {

    private lateinit var repository: GithubRepository
    private lateinit var sut: GetGithubRepositoriesUseCase

    @Before
    fun setup() {
        repository = mockk()
        sut = GetGithubRepositoriesUseCase(repository)
    }

    @Test
    fun `given repository returns success when invoke is called then return list of repositories`() =
        runTest {
            val expectedRepositories = TestMockData.repositoryList
            val expectedResult = Result.Success(expectedRepositories)
            coEvery { repository.getPublicRepositories() } returns expectedResult

            val result = sut()

            assertTrue(result is Result.Success)
            assertEquals(expectedRepositories, (result as Result.Success).data)
            coVerify(exactly = 1) { repository.getPublicRepositories() }
        }

    @Test
    fun `given repository returns empty list when invoke is called then return empty list`() =
        runTest {
            val expectedResult = Result.Success(TestMockData.emptyRepositoryList)
            coEvery { repository.getPublicRepositories() } returns expectedResult

            val result = sut()

            assertTrue(result is Result.Success)
            assertTrue((result as Result.Success).data.isEmpty())
            coVerify(exactly = 1) { repository.getPublicRepositories() }
        }

    @Test
    fun `given repository returns error when invoke is called then return error result`() =
        runTest {
            val errorMessage = TestMockData.ErrorMessages.NETWORK_ERROR
            val errorCode = TestMockData.ErrorCodes.INTERNAL_SERVER_ERROR
            val expectedResult = Result.Error(errorMessage, errorCode)
            coEvery { repository.getPublicRepositories() } returns expectedResult

            val result = sut()

            assertTrue(result is Result.Error)
            assertEquals(errorMessage, (result as Result.Error).message)
            assertEquals(errorCode, result.code)
            coVerify(exactly = 1) { repository.getPublicRepositories() }
        }

    @Test
    fun `given repository returns error without code when invoke is called then return error result without code`() =
        runTest {
            val errorMessage = TestMockData.ErrorMessages.UNKNOWN_ERROR
            val expectedResult = Result.Error(errorMessage)
            coEvery { repository.getPublicRepositories() } returns expectedResult

            val result = sut()

            assertTrue(result is Result.Error)
            assertEquals(errorMessage, (result as Result.Error).message)
            assertEquals(null, result.code)
            coVerify(exactly = 1) { repository.getPublicRepositories() }
        }
}
