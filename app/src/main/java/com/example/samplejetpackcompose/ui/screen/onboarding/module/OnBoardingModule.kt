package com.example.samplejetpackcompose.ui.screen.onboarding.module

import com.example.samplejetpackcompose.data.implementation.repository.ReqResRepositoryImpl
import com.example.samplejetpackcompose.data.remote.ReqResApi
import com.example.samplejetpackcompose.domain.repository.ReqResRepository
import com.example.samplejetpackcompose.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingModule {

    @Provides
    fun reqResRepository(reqResApi: ReqResApi): ReqResRepository {
        return ReqResRepositoryImpl(reqResApi)
    }

    @Provides
    fun provideLoginUseCase(reqResRepository: ReqResRepository): LoginUseCase {
        return LoginUseCase(reqResRepository)
    }
}