package com.vlad.simion.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.vlad.simion.domain.common.DataSourceType
import com.vlad.simion.domain.common.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "minderaApp_preferences")

class PreferencesManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PreferenceManager {

    private val isMockEnabledKey = booleanPreferencesKey("is_mock_enabled")

    override suspend fun saveDataSourceType(dataSourceType: DataSourceType) {
        val isMockEnabled = dataSourceType == DataSourceType.MOCK
        context.dataStore.edit { preferences ->
            preferences[isMockEnabledKey] = isMockEnabled
        }
    }

    override suspend fun getDataSourceType(): DataSourceType {
        val preferences = context.dataStore.data.first()
        val isMockEnabled = preferences[isMockEnabledKey] ?: false
        return if (isMockEnabled) {
            DataSourceType.MOCK
        } else {
            DataSourceType.REMOTE
        }
    }
}
