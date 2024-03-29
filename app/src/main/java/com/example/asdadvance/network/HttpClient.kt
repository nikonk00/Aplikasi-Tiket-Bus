package com.example.asdadvance.network

import com.bagicode.bagicodebaseutils.utils.Helpers
import com.example.asdadvance.BuildConfig
import com.example.asdadvance.BuildConfig.BASE_URL
import com.example.asdadvance.ui.BagicodeTravel
import com.readystatesoftware.chuck.Chuck
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client : Retrofit?= null
    private var endpoint : Endpoint?= null

    companion object {
        private val mInstance : HttpClient = HttpClient()
        @Synchronized
        fun getInstance() : HttpClient {
            return mInstance
        }
    }

    init {
        buildRetrofitClient()
    }

    fun getApi() : Endpoint? {
        if (endpoint == null) {
            endpoint = client!!.create(Endpoint::class.java)
        }
        return endpoint
    }

    private fun buildRetrofitClient() {
        val token = BagicodeTravel.getApp().getToken()
        buildRetrofitClient(token)
    }

    fun buildRetrofitClient(token : String?) {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG){
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(BagicodeTravel.getApp()))
        }

        if(token != null){
            builder.addInterceptor(getInInterceptorWithHeader("Authorization", "${token}"))
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL+"bcadvance/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Helpers.getDefaultGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        endpoint = null
    }

    private fun getInInterceptorWithHeader(headerName: String, headerValue: String): Interceptor {
        val header = HashMap<String, String>()
        header.put(headerName, headerValue)
        return  getInInterceptorWithHeader(header)

    }

    private fun getInInterceptorWithHeader(headers : Map<String, String>) : Interceptor {
        return Interceptor {
            var original = it.request()
            var builder = original.newBuilder()
            for ((key, value) in headers) {
                builder.addHeader(key, value)
            }
            builder.method(original.method(), original.body())
            it.proceed(builder.build())
        }
    }

}