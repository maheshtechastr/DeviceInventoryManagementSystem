package com.mpg.nagarro.deviceinventorymgmt.di

import com.mpg.nagarro.deviceinventorymgmt.data.InventoryDataSource
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.RepositoryImpl
import com.mpg.nagarro.deviceinventorymgmt.data.local.InventoryDao
import com.mpg.nagarro.deviceinventorymgmt.data.local.LocalInventoryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
//    @Binds
//    abstract fun getRepository(inventoryDataSource: RepositoryImpl): Repository
//
//    @Binds
//    abstract fun getLocalRepository(inventoryDao: LocalInventoryRepository): InventoryDataSource

    @Provides
    internal fun getRepository(inventoryDataSource: InventoryDataSource): Repository {
        return RepositoryImpl(inventoryDataSource)
    }
    @Provides
    internal fun getLocalRepository(inventoryDao: InventoryDao): InventoryDataSource {
        return LocalInventoryRepository(inventoryDao)
    }

}