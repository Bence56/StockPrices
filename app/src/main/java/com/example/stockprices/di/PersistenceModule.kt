package com.example.stockprices.di

import android.app.Application
import androidx.room.Room
import com.example.stockprices.R
import com.example.stockprices.persistance.AppDatabase
import com.example.stockprices.persistance.StockDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application): AppDatabase {
    return Room
      .databaseBuilder(
        application,
        AppDatabase::class.java,
        application.getString(R.string.database)
      )
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  @Singleton
  fun provideStockDao(appDatabase: AppDatabase): StockDao {
    return appDatabase.stockDao()
  }
}