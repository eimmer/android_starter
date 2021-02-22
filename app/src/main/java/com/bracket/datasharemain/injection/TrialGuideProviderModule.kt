package com.bracket.datasharemain.injection

import com.bracket.datasharemain.MainApplication
import com.bracket.datasharemain.TrialGuideProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class TrialGuideProviderModule {

    @Provides
    @Singleton
    fun provideTrialGuideProvider(mainApplication: MainApplication): TrialGuideProvider {
        return TrialGuideProvider(mainApplication)
    }
}