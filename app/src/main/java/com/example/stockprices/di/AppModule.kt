package com.example.stockprices.di


import com.example.stockprices.network.DetailedStockRepository
import com.example.stockprices.network.StockApiService
import com.example.stockprices.network.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val BASE_URL = ""

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideStockApiService(retrofit: Retrofit): StockApiService =
        retrofit.create(StockApiService::class.java)

    @Provides
    @Singleton
    fun provideStockRepository(apiService: StockApiService): StockRepository =
        StockRepository(apiService)

    @Provides
    @Singleton
    fun provideDetailedStockRepository(apiService: StockApiService): DetailedStockRepository =
        DetailedStockRepository(apiService)

}