package id.asep.fpasep.utils.helper

interface FoodSharedPreferences {

    fun getInt(keyName: String, defaultValue: Int): Int

    fun putInt(keyName: String, value: Int)

    fun getLong(keyName: String, defaultValue: Long): Long

    fun putLong(keyName: String, value: Long)

    fun putBoolean(keyName: String, value: Boolean)

    fun getFloat(keyName: String, defaultValue: Float): Float

    fun getBoolean(keyName: String, defaultValue: Boolean): Boolean

    fun getString(keyName: String, defaultValue: String): String

    fun putString(keyName: String, value: String)

    fun removeValue(keyName: String)

    fun clearAll()

    companion object {
        const val SHARED_PREF_KEY = "Food_Mobile_Shared_Preferences"
    }
}