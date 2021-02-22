package com.bracket.datasharemain.injection

import com.bracket.datasharemain.data.UserData
import com.bracket.datasharemain.network.CookingService
import com.bracket.datasharemain.network.ServiceApiInterceptor
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideCookingService(): CookingService {
        return CookingService(ServiceApiInterceptor("8e74945aa2ac44f282c68b814599b42f"))
    }
}