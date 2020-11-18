package com.task.nytimes.Components


import com.task.nytimes.Database.DbRepository
import com.task.nytimes.Helpers.DateHelper
import com.task.nytimes.Modules.ContextModule
import com.task.nytimes.Modules.DateModule
import com.task.nytimes.Modules.DbRepoModule
import com.task.nytimes.Modules.NetModule
import com.task.nytimes.ViewModels.NewsActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, DbRepoModule::class, NetModule::class, DateModule::class])
@Singleton
interface AppLevelComponent {
    fun inject(ma: NewsActivityViewModel)
    fun inject(dr: DbRepository)
    fun inject(dr: DateHelper)

}
