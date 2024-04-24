package com.example.syncmaster.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.syncmaster.data.database.dao.DeviceDao
import com.example.syncmaster.data.database.entities.DeviceEntity

//Pasamos las entidades con la version (migraciones)
@Database(entities = [DeviceEntity::class], version = 1)
//Clase abtracta porque no vamos a implementar aqui ningun metodo
abstract class DeviceDatabase : RoomDatabase() {

    abstract fun deviceDao(): DeviceDao

}
