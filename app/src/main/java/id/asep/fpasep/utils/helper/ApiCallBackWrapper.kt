package id.asep.fpasep.utils.helper

import id.asep.fpasep.utils.exception.ExceptionHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

abstract class ApiCallBackWrapper<T> : Callback<T> {
    abstract fun onSuccess(response: T?)

    abstract fun onFailure(message: ExceptionHandler)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(response.body())
        } else {
            response.errorBody()?.use { e ->
                onFailure(ExceptionHandler(response.code(), e.string()))
            }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t is HttpException) {
            t.response()?.errorBody()?.use { e ->
                onFailure(ExceptionHandler(t.code(), e.string()))
            }
        } else {
            onFailure(ExceptionHandler(-1, "Failed to connect to the server"))
        }
    }
}