package com.mrmar.airfryer.data.datasources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class SessionContextEntity(
    @PrimaryKey val accountId: String,
    @ColumnInfo(name = "token") val token: String?,
)