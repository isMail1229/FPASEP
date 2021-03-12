package id.asep.fpasep.datasource.repository.user.auth

import id.asep.fpasep.utils.helper.FoodSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sharedPreferences: FoodSharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = sharedPreferences.getString(UserAuthentication.PREF_TOKEN_KEY, "")
        val requestBuilder = chain.request().newBuilder()
        if (accessToken.isNotBlank()) {
            requestBuilder.addHeader(
                UserAuthentication.AUTHENTICATION_HEADER,
                "Bearer $accessToken"
            )
        }
        return chain.proceed(requestBuilder.build())
    }
}