package com.task.nytimes.Configurations

import android.app.Application
import com.task.nytimes.Components.AppLevelComponent
import com.task.nytimes.Components.DaggerAppLevelComponent
import com.task.nytimes.Modules.ContextModule
import com.task.nytimes.Modules.DateModule
import com.task.nytimes.Modules.DbRepoModule
import com.task.nytimes.Modules.NetModule



class App : Application() {
    lateinit var appLevelComponent: AppLevelComponent


    override fun onCreate() {
        super.onCreate()
        app = this
        // we only have to set constructor modules or context modules.
        appLevelComponent = DaggerAppLevelComponent.builder()
            .contextModule(ContextModule(this))
            .dbRepoModule(DbRepoModule())
            .netModule(NetModule("https://api.nytimes.com/svc/"))
            .dateModule(DateModule())
            .build()


    }

    companion object {
        lateinit var app: App
    }


}
