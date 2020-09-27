package com.mpg.nagarro.deviceinventorymgmt.di

import android.content.Context
import com.mpg.nagarro.deviceinventorymgmt.data.InventoryDataSource
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.RepositoryImpl
import com.mpg.nagarro.deviceinventorymgmt.data.local.InventoryDao
import com.mpg.nagarro.deviceinventorymgmt.data.local.InventoryDatabase
import com.mpg.nagarro.deviceinventorymgmt.data.local.LocalInventoryRepository
import com.mpg.nagarro.deviceinventorymgmt.factory.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
   internal fun viewModelFactoryProvider(repository: Repository): ViewModelFactory {

        return ViewModelFactory(repository)
    }

    @Provides
    internal fun getRepository(inventoryDataSource: InventoryDataSource): Repository {
        return RepositoryImpl(inventoryDataSource)
    }

    @Provides
    internal fun getLocalRepository(context: Context): InventoryDataSource {
        return LocalInventoryRepository(
            InventoryDatabase
                .getDatabase(context)
                .inventoryDao()
        )
    }

    @Provides
    internal fun getDao(context: Context): InventoryDao {
        return InventoryDatabase.getDatabase(context).inventoryDao()
    }

}