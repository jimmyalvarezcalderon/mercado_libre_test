package com.project.mercado_libre_test.repository.product.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.mercado_libre_test.services.api.products.SearchProductsDataSource
import com.project.mercado_libre_test.services.api.products.model.Product
import com.project.mercado_libre_test.repository.product.IProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepository : IProductRepository {
    override fun searchProducts(query: String): Flow<PagingData<Product>> {
        val config = PagingConfig(15)
        return Pager(
            config = config,
            pagingSourceFactory = { SearchProductsDataSource(query) }
        ).flow
    }
}