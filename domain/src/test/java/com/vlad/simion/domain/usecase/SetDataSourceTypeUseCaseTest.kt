package com.vlad.simion.domain.usecase

import com.vlad.simion.domain.common.DataSourceType
import com.vlad.simion.domain.common.PreferenceManager
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SetDataSourceTypeUseCaseTest {

    private lateinit var preferenceManager: PreferenceManager
    private lateinit var sut: SetDataSourceTypeUseCase

    @Before
    fun setup() {
        preferenceManager = mockk()
        sut = SetDataSourceTypeUseCase(preferenceManager)
    }

    @Test
    fun `given REMOTE data source type when invoke is called then save REMOTE preference`() =
        runTest {
            val dataSourceType = DataSourceType.REMOTE
            coEvery { preferenceManager.saveDataSourceType(dataSourceType) } just runs

            sut(dataSourceType)

            coVerify(exactly = 1) { preferenceManager.saveDataSourceType(dataSourceType) }
        }

    @Test
    fun `given MOCK data source type when invoke is called then save MOCK preference`() =
        runTest {
            val dataSourceType = DataSourceType.MOCK
            coEvery { preferenceManager.saveDataSourceType(dataSourceType) } just runs

            sut(dataSourceType)

            coVerify(exactly = 1) { preferenceManager.saveDataSourceType(dataSourceType) }
        }

    @Test
    fun `given multiple invocations when invoke is called then save each preference separately`() =
        runTest {
            coEvery { preferenceManager.saveDataSourceType(any()) } just runs

            sut(DataSourceType.REMOTE)
            sut(DataSourceType.MOCK)
            sut(DataSourceType.REMOTE)

            coVerify(exactly = 2) { preferenceManager.saveDataSourceType(DataSourceType.REMOTE) }
            coVerify(exactly = 1) { preferenceManager.saveDataSourceType(DataSourceType.MOCK) }
        }

    @Test
    fun `given REMOTE data source type when invoke is called multiple times then save preference each time`() =
        runTest {
            val dataSourceType = DataSourceType.REMOTE
            coEvery { preferenceManager.saveDataSourceType(dataSourceType) } just runs

            sut(dataSourceType)
            sut(dataSourceType)
            sut(dataSourceType)

            coVerify(exactly = 3) { preferenceManager.saveDataSourceType(dataSourceType) }
        }

    @Test
    fun `given MOCK data source type when invoke is called multiple times then save preference each time`() =
        runTest {
            val dataSourceType = DataSourceType.MOCK
            coEvery { preferenceManager.saveDataSourceType(dataSourceType) } just runs

            sut(dataSourceType)
            sut(dataSourceType)

            coVerify(exactly = 2) { preferenceManager.saveDataSourceType(dataSourceType) }
        }
}
