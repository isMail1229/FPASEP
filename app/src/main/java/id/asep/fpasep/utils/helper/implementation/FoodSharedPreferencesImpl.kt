package id.asep.fpasep.utils.helper.implementation

import android.content.SharedPreferences
import id.asep.fpasep.utils.helper.FoodSharedPreferences
import javax.inject.Inject

class FoodSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : FoodSharedPreferences {

    override fun getInt(keyName: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(keyName, defaultValue)
    }

    override fun putInt(keyName: String, value: Int) {
        sharedPreferences.edit().putInt(keyName, value).apply()
    }

    override fun getLong(keyName: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(keyName, defaultValue)
    }

    override fun putLong(keyName: String, value: Long) {
        sharedPreferences.edit().putLong(keyName, value).apply()
    }

    override fun putBoolean(keyName: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(keyName, value).apply()
    }

    override fun getFloat(keyName: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(keyName, defaultValue)
    }

    override fun getBoolean(keyName: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(keyName, defaultValue)
    }

    override fun getString(keyName: String, defaultValue: String): String {
        return sharedPreferences.getString(keyName, defaultValue) ?: defaultValue
    }

    override fun putString(keyName: String, value: String) {
        sharedPreferences.edit().putString(keyName, value).apply()
    }

    override fun removeValue(keyName: String) {
        sharedPreferences.edit().remove(keyName).apply()
    }

    override fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}