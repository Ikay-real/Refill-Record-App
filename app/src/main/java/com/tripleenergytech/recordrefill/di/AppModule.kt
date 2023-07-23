package com.tripleenergytech.recordrefill.di

import android.content.Context
import androidx.room.Room
import com.tripleenergytech.recordrefill.data.local.dao.CylinderDao
import com.tripleenergytech.recordrefill.data.local.dao.DaySessionDao
import com.tripleenergytech.recordrefill.data.local.dao.RefillRecordDao
import com.tripleenergytech.recordrefill.data.local.database.RefillRecordDatabase
import com.tripleenergytech.recordrefill.data.repository.CylinderRepositoryImpl
import com.tripleenergytech.recordrefill.data.repository.RefillRecordRepositoryImpl
import com.tripleenergytech.recordrefill.domain.repository.CylinderRepository
import com.tripleenergytech.recordrefill.domain.repository.RefillRecordRepository
import com.tripleenergytech.recordrefill.domain.usecase.addcylinderusecase.CylinderUseCaseImpl
import com.tripleenergytech.recordrefill.domain.usecase.addcylinderusecase.CylinderUseCase
import com.tripleenergytech.recordrefill.domain.usecase.managecylinderusecase.ManageCylinderUseCase
import com.tripleenergytech.recordrefill.domain.usecase.managecylinderusecase.ManageCylinderUseCaseImpl
import com.tripleenergytech.recordrefill.domain.usecase.refillrecordusecase.RefillRecordUseCase
import com.tripleenergytech.recordrefill.domain.usecase.refillrecordusecase.RefillRecordUseCaseImpl
import com.tripleenergytech.recordrefill.presentation.managecylinders.ManageCylindersScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): RefillRecordDatabase {
        return Room.databaseBuilder(
            context,
            RefillRecordDatabase::class.java,
            "refill_record"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRefillRecordDao(database: RefillRecordDatabase): RefillRecordDao {
        return database.refillRecordDao()
    }

    @Provides
    @Singleton
    fun provideRefillRecordRepository(refillRecordDao: RefillRecordDao,daySessionDao: DaySessionDao): RefillRecordRepository {
        return RefillRecordRepositoryImpl(refillRecordDao=refillRecordDao,daySessionDao)
    }
    @Provides
    @Singleton
    fun provideRefillRecordUseCase(refillRecordRepository: RefillRecordRepositoryImpl): RefillRecordUseCase {
        return RefillRecordUseCaseImpl(refillRecordRepository)
    }





    @Provides
    @Singleton
    fun provideCylinderDao(database: RefillRecordDatabase): CylinderDao {
        return database.cylinderDao()
    }

    @Provides
    @Singleton
    fun provideCylinderRepository(cylinderDao: CylinderDao): CylinderRepository {
        return CylinderRepositoryImpl(cylinderDao)
    }
    @Provides
    @Singleton
    fun provideCylinderUseCase(cylinderRepository: CylinderRepository): CylinderUseCase {
        return CylinderUseCaseImpl(cylinderRepository)
    }

//DaySessions
    @Provides
    fun provideDaySessionDao(database: RefillRecordDatabase): DaySessionDao {
        return database.daySessionDao()
    }




    //Manage Cylinders
    @Provides
    @Singleton
    fun provideManageCylinderUseCase(cylinderRepository: CylinderRepository): ManageCylinderUseCase {
        return ManageCylinderUseCaseImpl(cylinderRepository)
    }
}