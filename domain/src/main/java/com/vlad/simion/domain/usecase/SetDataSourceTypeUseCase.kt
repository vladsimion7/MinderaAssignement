package com.vlad.simion.domain.usecase

import com.vlad.simion.domain.common.DataSourceType
import com.vlad.simion.domain.common.PreferenceManager
import javax.inject.Inject

class SetDataSourceTypeUseCase @Inject constructor(private val preferenceManager: PreferenceManager) {
    suspend operator fun invoke(dataSource: DataSourceType) =
        preferenceManager.saveDataSourceType(dataSource)
}