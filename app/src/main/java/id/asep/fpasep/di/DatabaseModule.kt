package id.asep.fpasep.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.asep.fpasep.FoodApplication
import id.asep.fpasep.datasource.local.FoodDatabase
import id.asep.fpasep.datasource.local.dao.*
import id.asep.fpasep.datasource.local.service.UserService
import id.asep.fpasep.datasource.local.service.implementation.UserServiceImpl
import id.asep.fpasep.utils.helper.FoodSharedPreferences
import id.asep.fpasep.utils.helper.implementation.FoodSharedPreferencesImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: FoodApplication): FoodDatabase {
        return Room.databaseBuilder(
            app,
            FoodDatabase::class.java,
            FoodDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDAO(db: FoodDatabase): UserDAO {
        return db.userDAO()
    }

    @Singleton
    @Provides
    fun provideFoodDAO(db: FoodDatabase): FoodDAO {
        return db.foodDAO()
    }

    @Singleton
    @Provides
    fun provideTransactionDAO(db: FoodDatabase): TransactionDAO {
        return db.transactionDAO()
    }

    @Singleton
    @Provides
    fun provideIngredientDAO(db: FoodDatabase): IngredientDAO {
        return db.ingredientDAO()
    }

    @Singleton
    @Provides
    fun provideRateDAO(db: FoodDatabase): RateDAO {
        return db.rateDAO()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            FoodSharedPreferences.SHARED_PREF_KEY,
            Context.MODE_PRIVATE
        )
    }

    @Singleton
    @Provides
    fun provideFoodSharedPreferences(
        sharedPreferences: SharedPreferences
    ): FoodSharedPreferences {
        return FoodSharedPreferencesImpl(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideUserServices(userDAO: UserDAO): UserService {
        return UserServiceImpl(
            userDAO
        )
    }
}