package com.tripleenergytech.recordrefill.di

import android.content.Context
import androidx.room.Room
import com.tripleenergytech.recordrefill.data.local.dao.RefillRecordDao
import com.tripleenergytech.recordrefill.data.local.database.RefillRecordDatabase
import com.tripleenergytech.recordrefill.data.repository.RefillRecordRepositoryImpl
import com.tripleenergytech.recordrefill.domain.repository.RefillRecordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


object RecordsModule