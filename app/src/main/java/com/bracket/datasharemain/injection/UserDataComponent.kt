package com.bracket.datasharemain.injection

import com.bracket.datasharemain.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [UserDataModule::class, NetworkModule::class, TrialGuideProviderModule::class])
@Singleton
interface UserDataComponent {
    fun inject(fragment: MainFragment)
}