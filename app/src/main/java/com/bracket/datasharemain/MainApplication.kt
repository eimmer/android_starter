package com.bracket.datasharemain

import android.app.Application
import com.bracket.datasharemain.injection.ApplicationModule
import com.bracket.datasharemain.injection.DaggerUserDataComponent
import com.bracket.datasharemain.injection.UserDataComponent

class MainApplication : Application() {

    companion object {
        lateinit var dataProvider: UserDataComponent
    }

    override fun onCreate() {
        super.onCreate()
        dataProvider = DaggerUserDataComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}