package com.project.base_project.services.api

import arrow.syntax.function.memoize
import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtils {

    private const val BASE_URL = ""
    private val gson: Gson = Gson()

    fun getRetrofitClient(url: String): Single<Retrofit> {
        return Single.defer { Single.just(retrofitClient(url)) }
    }

    private val retrofitClient: (String) -> Retrofit = { url: String ->
        buildRetrofit(url, OkHttpClient.Builder().build())
    }.memoize()

    private fun buildRetrofit(url: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}