package com.vlad.simion.domain.usecase

import com.vlad.simion.domain.TestMockData
import com.vlad.simion.domain.common.Result
import com.vlad.simion.domain.model.response.GithubRepositoryDetailsEntity
import com.vlad.simion.domain.model.response.LicenseEntity
import com.vlad.simion.domain.model.response.OwnerEntity
import com.vlad.simion.domain.repository.GithubRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetGithubRepositoryDetailsUseCaseTest {

    private lateinit var repository: GithubRepository
    private lateinit var sut: GetGithubRepositoryDetailsUseCase

    @Before
    fun setup() {
        repository = mockk()
        sut = GetGithubRepositoryDetailsUseCase(repository)
    }

    @Test
    fun `given repository returns success when invoke is called with path then return repository details`() =
        runTest {
            val repositoryPath = TestMockData.Paths.DEFAULT_PATH
            val repositoryDetails = TestMockData.fullRepositoryDetails
            val expectedResult = Result.Success(repositoryDetails)
            coEvery { repository.getRepositoryDetails(repositoryPath) } returns expectedResult

            val result = sut(repositoryPath)

            assertTrue(result is Result.Success)
            assertEquals(repositoryDetails, (result as Result.Success).data)
            coVerify(exactly = 1) { repository.getRepositoryDetails(repositoryPath) }
        }

    @Test
    fun `given repository returns success with minimal data when invoke is called then return repository details with nulls`() =
        runTest {
            val repositoryPath = TestMockData.Paths.DEFAULT_PATH
            val repositoryDetails = TestMockData.minimalRepositoryDetails
            val expectedResult = Result.Success(repositoryDetails)
            coEvery { repository.getRepositoryDetails(repositoryPath) } returns expectedResult

            val result = sut(repositoryPath)

            assertTrue(result is Result.Success)
            assertEquals(repositoryDetails, (result as Result.Success).data)
            coVerify(exactly = 1) { repository.getRepositoryDetails(repositoryPath) }
        }

    @Test
    fun `given repository returns error when invoke is called then return error result`() =
        runTest {
            val repositoryPath = TestMockData.Paths.DEFAULT_PATH
            val errorMessage = TestMockData.ErrorMessages.REPOSITORY_NOT_FOUND
            val errorCode = TestMockData.ErrorCodes.NOT_FOUND
            val expectedResult = Result.Error(errorMessage, errorCode)
            coEvery { repository.getRepositoryDetails(repositoryPath) } returns expectedResult

            val result = sut(repositoryPath)

            assertTrue(result is Result.Error)
            assertEquals(errorMessage, (result as Result.Error).message)
            assertEquals(errorCode, result.code)
            coVerify(exactly = 1) { repository.getRepositoryDetails(repositoryPath) }
        }

    @Test
    fun `given repository returns network error when invoke is called then return error result`() =
        runTest {
            val repositoryPath = TestMockData.Paths.DEFAULT_PATH
            val errorMessage = TestMockData.ErrorMessages.NETWORK_TIMEOUT
            val errorCode = TestMockData.ErrorCodes.REQUEST_TIMEOUT
            val expectedResult = Result.Error(errorMessage, errorCode)
            coEvery { repository.getRepositoryDetails(repositoryPath) } returns expectedResult

            val result = sut(repositoryPath)

            assertTrue(result is Result.Error)
            assertEquals(errorMessage, (result as Result.Error).message)
            assertEquals(errorCode, result.code)
            coVerify(exactly = 1) { repository.getRepositoryDetails(repositoryPath) }
        }

    @Test
    fun `given different repository paths when invoke is called multiple times then call repository with correct paths`() =
        runTest {
            val path1 = TestMockData.Paths.PATH_1
            val path2 = TestMockData.Paths.PATH_2
            val details1 = TestMockData.repositoryDetails1
            val details2 = TestMockData.repositoryDetails2
            coEvery { repository.getRepositoryDetails(path1) } returns Result.Success(details1)
            coEvery { repository.getRepositoryDetails(path2) } returns Result.Success(details2)

            val result1 = sut(path1)
            val result2 = sut(path2)

            assertTrue(result1 is Result.Success)
            assertEquals(details1, (result1 as Result.Success).data)
            assertTrue(result2 is Result.Success)
            assertEquals(details2, (result2 as Result.Success).data)
            coVerify(exactly = 1) { repository.getRepositoryDetails(path1) }
            coVerify(exactly = 1) { repository.getRepositoryDetails(path2) }
        }
}
