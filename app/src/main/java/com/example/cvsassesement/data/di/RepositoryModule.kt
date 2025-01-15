package com.example.cvsassesement.data.di

import com.example.cvsassesement.data.respository.RepositoryImpl
import com.example.cvsassesement.domain.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): IRepository
}