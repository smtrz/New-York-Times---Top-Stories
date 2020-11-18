package com.task.nytimes.Modules

import android.content.Context

import androidx.room.Room

import com.task.nytimes.Database.AppDB
import com.task.nytimes.Database.DbRepository


import javax.inject.Singleton

import dagger.Module
import dagger.Provides


@Module
class DbRepoModule {


    @Provides
    @Singleton
    fun getRepository(c: Context): DbRepository {

        return DbRepository(c)

    }

    @Provides
    @Singleton
    fun getAppDb(c: Context): AppDB {
        return Room.databaseBuilder(c, AppDB::class.java, "userdb").build()
        //        ;

    }

}