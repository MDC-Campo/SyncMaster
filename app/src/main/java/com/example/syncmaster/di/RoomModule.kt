package com.example.syncmaster.di

import android.content.Context
import androidx.room.Room
import com.example.syncmaster.data.database.DeviceDatabase
import com.example.syncmaster.data.database.dao.DeviceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DEVICE_DATABASE_NAME = "device_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DeviceDatabase::class.java, DEVICE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDeviceDao(database: DeviceDatabase) = database.deviceDao()
}