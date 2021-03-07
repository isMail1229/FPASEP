package id.asep.fpasep.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "user", inheritSuperIndices = true)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "house_number") val houseNumber: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "roles") val roles: Roles,
    @ColumnInfo(name = "profile_url") val profilePicturePath: String?
) {

    class UserRolesConverter {
        @TypeConverter
        fun fromStringToRoles(value: String): Roles {
            return when (value) {
                Roles.Admin.codeName -> Roles.Admin
                Roles.User.codeName -> Roles.User
                else -> Roles.User
            }
        }

        @TypeConverter
        fun fromRolesToString(roles: Roles): String {
            return roles.codeName
        }
    }

    open class Roles(val codeName: String) {
        object Admin : Roles("ADMIN")
        object User : Roles("USER")
    }
}
