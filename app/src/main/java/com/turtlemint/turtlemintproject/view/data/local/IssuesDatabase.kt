package com.turtlemint.turtlemintproject.view.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.turtlemint.turtlemintproject.view.data.model.Issue
import com.turtlemint.turtlemintproject.view.data.model.User
import com.turtlemint.turtlemintproject.view.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider
// database initialization
@Database(entities = [Issue::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class IssuesDatabase : RoomDatabase() {

    abstract fun getIssuesDao(): IssuesDao

    class Callback @Inject constructor(
        private val database: Provider<IssuesDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}