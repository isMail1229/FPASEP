package id.asep.fpasep.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "rate", inheritSuperIndices = true)
data class Rate(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "review") val review: Long,
    @ColumnInfo(name = "food_id") val foodId: Long,
    @ColumnInfo(name = "transaction_id") val transactionId: Long,
    @ColumnInfo(name = "review_text") val textReview: String?,
    @ColumnInfo(name = "photo") val reviewPhoto: String?,
    @ColumnInfo(name = "description") val reviewDescription: String?,
    @ColumnInfo(name = "shop_services") val shopServices: ServicesShop,
) {
    class ServicesConverter {
        @TypeConverter
        fun fromStringToServiceShop(values: String): ServicesShop {
            return when (values) {
                ServicesShop.Bad.codename -> ServicesShop.Bad
                ServicesShop.Medium.codename -> ServicesShop.Medium
                ServicesShop.Good.codename -> ServicesShop.Good
                else -> ServicesShop.None
            }
        }

        @TypeConverter
        fun fromServiceShopToString(serviceShop: ServicesShop): String {
            return serviceShop.codename
        }
    }

    open class ServicesShop(val codename: String) {
        object Bad : ServicesShop("bad")
        object Medium : ServicesShop("medium")
        object Good : ServicesShop("good")
        object None : ServicesShop("none")
    }
}
