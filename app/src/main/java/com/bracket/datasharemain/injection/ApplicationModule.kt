package com.bracket.datasharemain.injection

import com.bracket.datasharemain.MainApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val app: MainApplication) {

    @Provides
    fun provideApplication(): MainApplication {
        return app
    }
}