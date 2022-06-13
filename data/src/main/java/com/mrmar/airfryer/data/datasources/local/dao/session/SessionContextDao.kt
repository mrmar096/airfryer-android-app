package com.mrmar.airfryer.data.datasources.local.dao.session

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrmar.airfryer.data.datasources.local.entities.SessionContextEntity

@Dao
internal interface SessionContextDao {
    @Query("SELECT token FROM SessionContextEntity")
    suspend fun getToken(): String?

    @Query("SELECT * FROM SessionContextEntity")
    suspend fun getSessionContext(): SessionContextEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(sessionContext: SessionContextEntity)

    @Query("DELETE FROM SessionContextEntity")
    suspend fun delete()
}