package com.project.mercado_libre_test.services.api

import arrow.syntax.function.memoize
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtils {

    const val BASE_URL = "https://api.mercadolibre.com/sites/MCO/"
    private val gson: Gson = Gson()

    fun getRetrofitClient(url: String): Retrofit {
        return retrofitClient(url)
    }

    private val retrofitClient: (String) -> Retrofit = { url: String ->
        buildRetrofit(url, OkHttpClient.Builder().build())
    }.memoize()

    private fun buildRetrofit(url: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}