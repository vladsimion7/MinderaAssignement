package com.vlad.simion.domain.common

interface PreferenceManager {
    suspend fun saveDataSourceType(dataSourceType: DataSourceType)
    suspend fun getDataSourceType(): DataSourceType
}