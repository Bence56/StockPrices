package com.example.stockprices

import android.content.Context
import androidx.room.Room
import com.example.stockprices.persistance.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context):AppDatabase{
        return Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java)
            .allowMainThreadQueries().build()
    }
}