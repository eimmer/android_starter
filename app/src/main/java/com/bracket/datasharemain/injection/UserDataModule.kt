package com.bracket.datasharemain.injection

import com.bracket.datasharemain.data.UserData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDataModule {
    @Provides
    @Singleton
    fun provideUserData(): UserData {
        return UserData()
    }
}