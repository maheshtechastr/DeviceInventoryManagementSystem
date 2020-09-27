package com.mpg.nagarro.deviceinventorymgmt.di

import android.app.Application
import androidx.room.Room
import com.mpg.nagarro.deviceinventorymgmt.data.local.InventoryDao
import com.mpg.nagarro.deviceinventorymgmt.data.local.InventoryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DbModule {

    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    fun provideDatabase(applicationContext: Application): InventoryDatabase {
        return Room.databaseBuilder(
            applicationContext, InventoryDatabase::class.java, "InventoryDatabase.db")
            .allowMainThreadQueries().build()
    }


    /*
     * We need the InventoryDao module.
     * For this, We need the InventoryDatabase object
     * So we will define the providers for this here in this module.
     * */
    @Provides
    @Singleton
     fun provideInventoryDao(inventoryDatabase: InventoryDatabase): InventoryDao {
        return inventoryDatabase.inventoryDao()
    }
}