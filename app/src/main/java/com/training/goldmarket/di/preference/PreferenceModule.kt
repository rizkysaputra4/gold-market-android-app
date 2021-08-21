package com.training.goldmarket.di.preference

import android.app.Application
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.training.goldmarket.data.preference.SharedPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Singleton
    @Provides
    fun providerSharedPref(application: Application): SharedPreference {
        val masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val pref = EncryptedSharedPreferences.create(
            "SharedPref",
            masterKeys,
            application,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        return SharedPreference(pref)
    }
}