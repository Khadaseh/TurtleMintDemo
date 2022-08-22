package com.turtlemint.turtlemintproject.view.di

import android.app.Application
import androidx.room.Room
import com.turtlemint.turtlemintproject.view.data.local.IssuesDao
import com.turtlemint.turtlemintproject.view.data.local.IssuesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: IssuesDatabase.Callback): IssuesDatabase{
        return Room.databaseBuilder(application, IssuesDatabase::class.java, "news_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideissueDao(db: IssuesDatabase): IssuesDao {
        return db.getIssuesDao()
    }
}