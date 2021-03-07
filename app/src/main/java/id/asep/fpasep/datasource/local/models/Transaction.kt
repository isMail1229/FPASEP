package id.asep.fpasep.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "transaction", inheritSuperIndices = true)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "food_id") val foodId: Long,
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "quantity") val quantity: Long,
    @ColumnInfo(name = "total") val total: String,
    @ColumnInfo(name = "status") val status: TransactionStatus,
    @ColumnInfo(name = "payment_url") val paymentUrl: String
) {

    class TransactionConverter {
        @TypeConverter
        fun fromStringToStatus(value: String): TransactionStatus {
            return when (value) {
                TransactionStatus.Pending.codeName -> TransactionStatus.Pending
                TransactionStatus.OnDelivery.codeName -> TransactionStatus.OnDelivery
                TransactionStatus.Delivery.codeName -> TransactionStatus.Delivery
                TransactionStatus.Cancelled.codeName -> TransactionStatus.Cancelled
                else -> TransactionStatus.Pending
            }
        }

        @TypeConverter
        fun fromStatusToString(status: TransactionStatus): String {
            return status.codeName
        }
    }

    open class TransactionStatus(val codeName: String) {
        object Pending : TransactionStatus("PENDING")
        object OnDelivery : TransactionStatus("ON_DELIVERY")
        object Delivery : TransactionStatus("DELIVERED")
        object Cancelled : TransactionStatus("CANCELLED")
    }
}