package com.mrmar.airfryer.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrmar.airfryer.data.datasources.local.dao.session.SessionContextDao
import com.mrmar.airfryer.data.datasources.local.entities.SessionContextEntity

@Database(entities = [SessionContextEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase(){
    abstract fun sessionContextDao(): SessionContextDao
}