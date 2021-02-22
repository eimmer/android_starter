package com.bracket.datasharemain.injection

import com.bracket.datasharemain.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ApplicationModule::class])
@Singleton
interface UserDataComponent {
    fun inject(fragment: MainFragment)
}