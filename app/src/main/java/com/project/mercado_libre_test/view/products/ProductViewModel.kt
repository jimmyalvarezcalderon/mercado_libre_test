package com.project.mercado_libre_test.view.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.mercado_libre_test.repository.product.IProductRepository
import com.project.mercado_libre_test.services.api.products.model.Product
import kotlinx.coroutines.flow.Flow

class ProductViewModel(private val productRepository: IProductRepository) : ViewModel() {

    private var productsStream: Flow<PagingData<Product>>? = null
    private var currentQueryValue: String? = null

    fun searchRepo(queryString: String): Flow<PagingData<Product>> {
        val lastResult = productsStream
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult = productRepository.searchProducts(queryString)
            .cachedIn(viewModelScope)
        productsStream = newResult
        return newResult
    }
}