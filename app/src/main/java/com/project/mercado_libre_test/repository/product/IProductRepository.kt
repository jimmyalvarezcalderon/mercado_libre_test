package com.project.mercado_libre_test.repository.product

import androidx.paging.PagingData
import com.project.mercado_libre_test.services.api.products.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun searchProducts(query: String): Flow<PagingData<Product>>
}