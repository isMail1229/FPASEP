package id.asep.fpasep.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.asep.fpasep.datasource.repository.user.auth.AuthInterceptor
import id.asep.fpasep.utils.common.FoodConstants.Companion.BASE_URL
import id.asep.fpasep.utils.helper.FoodSharedPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideInterceptor(
        sharedPreferences: FoodSharedPreferences
    ): Interceptor {
        return AuthInterceptor(
            sharedPreferences
        )
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        interceptor: AuthInterceptor
    ): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }
}