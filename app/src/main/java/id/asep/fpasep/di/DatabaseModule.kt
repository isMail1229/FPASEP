package id.asep.fpasep.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.asep.fpasep.FoodApplication
import id.asep.fpasep.datasource.local.FoodDatabase
import id.asep.fpasep.datasource.local.dao.*
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

}