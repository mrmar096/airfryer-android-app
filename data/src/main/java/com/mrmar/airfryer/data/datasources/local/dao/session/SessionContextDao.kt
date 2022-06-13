package com.mrmar.airfryer.data.datasources.local.dao.session

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mrmar.airfryer.data.datasources.local.entities.SessionContextEntity

@Dao
internal interface SessionContextDao {
    @Query("SELECT token FROM SessionContextEntity")
    suspend fun getToken(): String?

    @Query("SELECT * FROM SessionContextEntity")
    fun getSessionContext(): LiveData<SessionContextEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(sessionContext: SessionContextEntity)

    @Delete
    fun delete(user: SessionContextEntity)
}