package id.asep.fpasep.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.asep.fpasep.datasource.local.dao.*
import id.asep.fpasep.datasource.local.models.*

@Database(
    entities = [
        User::class,
        Food::class,
        Transaction::class,
        Rate::class,
        Ingredient::class
    ],
    exportSchema = true,
    version = 1
)
@TypeConverters(
    User.UserRolesConverter::class,
    Transaction.TransactionConverter::class,
    Rate.ServicesConverter::class
)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    abstract fun foodDAO(): FoodDAO

    abstract fun transactionDAO(): TransactionDAO

    abstract fun rateDAO(): RateDAO

    abstract fun ingredientDAO(): IngredientDAO

    companion object {
        const val DATABASE_NAME = "Food-Database"
    }
}