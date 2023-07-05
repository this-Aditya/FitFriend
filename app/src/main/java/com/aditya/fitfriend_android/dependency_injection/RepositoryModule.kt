package com.aditya.fitfriend_android.dependency_injection

import com.aditya.fitfriend_android.data.dao.ActivityTransitionDao
import com.aditya.fitfriend_android.data.dao.AsanaDao
import com.aditya.fitfriend_android.data.dao.MeditationDao
import com.aditya.fitfriend_android.data.dao.PranayamDao
import com.aditya.fitfriend_android.data.dao.SleepClassifyDao
import com.aditya.fitfriend_android.data.dao.SleepSegmentDao
import com.aditya.fitfriend_android.network.AsanaAPI
import com.aditya.fitfriend_android.network.MeditationAPI
import com.aditya.fitfriend_android.network.PranayamAPI
import com.aditya.fitfriend_android.repository.YogaRepository
import com.aditya.fitfriend_android.repository.YogaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideYogaRepository(
        asanaDao: AsanaDao,
        meditationDao: MeditationDao,
        pranayamDao: PranayamDao,
        sleepClassifyDao: SleepClassifyDao,
        sleepSegmentDao: SleepSegmentDao,
        activityTransitionDao: ActivityTransitionDao,
        asanaAPI: AsanaAPI,
        meditationAPI: MeditationAPI,
        pranayamAPI: PranayamAPI
    ): YogaRepository = YogaRepositoryImpl(
        asanaDao,
        meditationDao,
        pranayamDao,
        sleepClassifyDao,
        sleepSegmentDao,
        activityTransitionDao,
        asanaAPI,
        meditationAPI,
        pranayamAPI
    )
}