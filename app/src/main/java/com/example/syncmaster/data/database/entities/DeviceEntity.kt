package com.example.syncmaster.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "devices")
data class DeviceEntity(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    val phoneId: String,
    val userName: String,
    val model: String
)