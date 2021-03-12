package id.asep.fpasep.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.asep.fpasep.datasource.local.service.UserService
import id.asep.fpasep.datasource.repository.user.UserRepository
import id.asep.fpasep.datasource.repository.user.implementation.UserRepositoryImpl
import id.asep.fpasep.utils.helper.FoodSharedPreferences
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        plainRetrofit: Retrofit,
        sharedPreferences: FoodSharedPreferences,
        userService: UserService
    ): UserRepository {
        return UserRepositoryImpl(
            plainRetrofit,
            sharedPreferences,
            userService
        )
    }
}