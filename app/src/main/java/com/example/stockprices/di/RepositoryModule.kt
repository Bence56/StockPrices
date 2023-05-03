package com.example.stockprices.di

import com.example.stockprices.model.StockRepository
import com.example.stockprices.persistance.StockDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
    stockDao: StockDao
  ): StockRepository {
    return StockRepository(stockDao)
  }
}