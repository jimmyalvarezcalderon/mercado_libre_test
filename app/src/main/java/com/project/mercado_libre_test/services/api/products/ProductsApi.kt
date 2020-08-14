package com.project.mercado_libre_test.services.api.products

import com.project.mercado_libre_test.services.api.products.model.ProductSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {

    @GET("search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("offset") page: Int,
        @Query("limit") products: Int
    ): ProductSearchResponse
}