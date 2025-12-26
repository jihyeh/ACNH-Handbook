package org.jihye.acnhhb.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class AndroidDataStoreHelper : KoinComponent {
    val context: Context by inject()
}

actual fun createDataStore(): DataStore<Preferences> {
    val context = AndroidDataStoreHelper().context
    return PreferenceDataStoreFactory.createWithPath(
            produceFile = { context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath.toPath() }
    )
}
