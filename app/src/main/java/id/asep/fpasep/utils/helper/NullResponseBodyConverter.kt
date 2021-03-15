package id.asep.fpasep.utils.helper

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * taken from https://github.com/square/retrofit/issues/1554#issuecomment-236202478
 */

class NullResponseBodyConverter : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, Any?> {
        val delegate = retrofit.nextResponseBodyConverter<Any?>(this, type, annotations)
        return Converter { value ->
            if (value.contentLength() != 0L) delegate.convert(value) else null
        }
    }
}